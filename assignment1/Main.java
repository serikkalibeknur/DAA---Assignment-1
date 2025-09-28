package com.beknur;

import java.util.Random;

public class Main {
    private static final String CSV_FILE = "metrics.csv";
    private static final Random rand = new Random();

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -cp target/assignment1-1.0-SNAPSHOT.jar com.beknur.Main <algorithm> <size> [<k>]");
            System.out.println("Algorithms: mergesort, quicksort, select, closestpair");
            return;
        }

        String algo = args[0].toLowerCase();
        int size = Integer.parseInt(args[1]);
        int k = args.length > 2 ? Integer.parseInt(args[2]) : -1;

        switch (algo) {
            case "mergesort":
                runMergeSort(size);
                break;
            case "quicksort":
                runQuickSort(size);
                break;
            case "select":
                if (k < 0 || k >= size) {
                    System.out.println("Invalid k for select (0 to " + (size - 1) + ")");
                    return;
                }
                runSelect(size, k);
                break;
            case "closestpair":
                runClosestPair(size);
                break;
            default:
                System.out.println("Unknown algorithm: " + algo);
                return;
        }
    }

    private static void runMergeSort(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = rand.nextInt(1000);
        MergeSort.sort(arr);
        Metrics.metrics.writeToCSV(CSV_FILE, size, "mergesort");
        System.out.println("MergeSort completed. Comparisons: " + MergeSort.getComparisonCount() +
                ", Depth: " + MergeSort.getMaxDepth());
    }

    private static void runQuickSort(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = rand.nextInt(1000);
        QuickSort.sort(arr);
        Metrics.metrics.writeToCSV(CSV_FILE, size, "quicksort");
        System.out.println("QuickSort completed. Comparisons: " + QuickSort.getComparisonCount() +
                ", Depth: " + QuickSort.getMaxDepth());
    }

    private static void runSelect(int size, int k) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = rand.nextInt(1000);
        int result = DeterministicSelect.select(arr, k);
        Metrics.metrics.writeToCSV(CSV_FILE, size, "select");
        System.out.println("Select completed. kth element: " + result + ", Comparisons: " +
                DeterministicSelect.getComparisonCount() + ", Depth: " + DeterministicSelect.getMaxDepth());
    }

    private static void runClosestPair(int size) {
        ClosestPair.Point[] points = new ClosestPair.Point[size];
        for (int i = 0; i < size; i++) {
            points[i] = new ClosestPair.Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
        }
        ClosestPair.Pair result = ClosestPair.closestPair(points);
        Metrics.metrics.writeToCSV(CSV_FILE, size, "closestpair");
        System.out.println("ClosestPair completed. Distance: " + result.distance + ", Comparisons: " +
                ClosestPair.getComparisonCount() + ", Depth: " + ClosestPair.getMaxDepth());
    }
}
