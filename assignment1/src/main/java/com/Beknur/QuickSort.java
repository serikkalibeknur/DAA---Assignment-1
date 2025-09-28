package com.Beknur;

import java.util.Random;

public class QuickSort {
    private static Metrics metrics = new Metrics();
    private static Random rand = new Random();

    public static void sort(int[] arr) {
        metrics.reset();
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int p = partition(arr, lo, hi);
            if (p - lo < hi - p) { // Recurse smaller partition first
                sort(arr, lo, p - 1);
                sort(arr, p + 1, hi);
            } else {
                sort(arr, p + 1, hi);
                sort(arr, lo, p - 1);
            }
            metrics.increaseDepth();
        }
    }

    private static int partition(int[] arr, int lo, int hi) {
        int pivotIdx = lo + rand.nextInt(hi - lo + 1); // Randomized pivot
        swap(arr, pivotIdx, hi);
        int pivot = arr[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            metrics.incrementComparisons(1);
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, hi);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        metrics.incrementSwaps(1);
    }

    // For testing
    public static int getMaxDepth() { return metrics.getMaxDepth(); }
    public static int getComparisonCount() { return metrics.getComparisonCount(); }
    public static int getSwapCount() { return metrics.getSwapCount(); }
}