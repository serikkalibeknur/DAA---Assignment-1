# Assignment 1: Divide-and-Conquer Algorithms

This project implements four divide-and-conquer algorithms: MergeSort, QuickSort, Deterministic Select, and Closest Pair of Points. The implementation includes a CLI for execution, performance metrics tracking, and automated testing, all managed via a GitHub workflow.

## Architecture Notes
- **Depth Control**: Recursion depth is managed by prioritizing smaller partitions first (e.g., QuickSort) or using a deterministic pivot (e.g., Select’s Median-of-Medians), ensuring stack usage remains O(log n) or constant where applicable.
- **Memory Allocations**: A reusable buffer is employed in MergeSort to minimize dynamic memory allocation, while Closest Pair uses a strip array of bounded size (7-8 points) to optimize space.

## Recurrence Analysis
### MergeSort
- **Recurrence**: T(n) = 2T(n/2) + O(n), where merging takes linear time.
- **Master Theorem**: Case 2 applies (a=2, b=2, f(n)=O(n), log_b a = log_2 2 = 1). Since f(n) = O(n^1) and n^log_b a = n^1, T(n) = Θ(n log n).
- **Insight**: The tight bound reflects the balanced splits and linear merge cost.

### QuickSort
- **Recurrence**: Average case T(n) = 2T(n/2) + O(n), assuming a good pivot splits evenly.
- **Master Theorem**: Similar to MergeSort, Case 2 (a=2, b=2, f(n)=O(n)) yields Θ(n log n). Worst case (e.g., sorted input) is O(n²), but randomized pivots and smaller-first recursion limit this to O(log n) depth.
- **Insight**: The average-case analysis holds due to pivot randomization, with depth bounded by design.

### Deterministic Select
- **Recurrence**: T(n) = T(n/5) + T(7n/10) + O(n), from Median-of-Medians (grouping into 5, recursing on 7/10 after pivot).
- **Akra-Bazzi**: Solve p where n^p = (1/5)^p + (7/10)^p. Numerically, p ≈ 0.92 < 1, so T(n) = Θ(n).
- **Insight**: The linear bound is guaranteed by the deterministic pivot choice, avoiding worst-case scenarios.

### Closest Pair
- **Recurrence**: T(n) = 2T(n/2) + O(n log n), from sorting by x and y, plus strip processing.
- **Master Theorem**: Case 3 (a=2, b=2, f(n)=O(n log n), n^log_b a log^1 n = n log n), so T(n) = O(n log n).
- **Insight**: The strip check (O(n)) dominates recursion, maintaining the overall complexity.

## Plots
- **Time vs n**: Plots show execution time (ns) vs. input size (n) for each algorithm, generated from `metrics.csv` data. Expect MergeSort and QuickSort to scale as n log n, Select as n, and Closest Pair with a slight curve due to strip overhead.
- **Depth vs n**: Displays maximum recursion depth vs. n. QuickSort and MergeSort should approximate 2 * floor(log₂ n) + O(1), Select remains shallow, and Closest Pair follows a log n pattern.
- **Generation**: Use the CLI (`java -cp target/assignment1-1.0-SNAPSHOT.jar com.beknur.Main <algo> <size>`) to collect data, then plot with Excel or Python (e.g., Matplotlib).

## Summary
- **Theoretical vs. Measured**: The measured times align with theoretical complexities (n log n for MergeSort/QuickSort/Closest Pair, n for Select), though constants vary due to cache effects and garbage collection. Depth bounds hold as designed.
- **Mismatches**: QuickSort may show higher variance due to pivot luck, and Closest Pair’s strip check introduces a small overhead not fully captured by the recurrence.
- **Improvements**: Consider hybrid approaches (e.g., insertion sort for small n) or parallelization for larger datasets.

## Repository Structure
- `src/main/java/com/beknur/`: Algorithm implementations (MergeSort.java, QuickSort.java, DeterministicSelect.java, ClosestPair.java, Main.java, Metrics.java, Util.java).
- `src/test/java/com/beknur/`: Test files (AppTest.java, MergeSortTest.java, QuickSortTest.java, SelectTest.java, ClosestPairTest.java).
- `.github/workflows/ci.yml`: GitHub Actions for CI.
- `metrics.csv`: Performance data output.

## How to Run
1. Build: `mvn clean package`
2. Run: `java -cp target/assignment1-1.0-SNAPSHOT.jar com.beknur.Main <algorithm> <size> [<k>]`
   - Example: `java -cp target/assignment1-1.0-SNAPSHOT.jar com.beknur.Main quicksort 100`

## License
[Add a license if required, e.g., MIT]
