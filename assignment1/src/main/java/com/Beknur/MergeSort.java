package com.yourname;

import java.util.Arrays;

public class MergeSort {
    private static Metrics metrics = new Metrics();
    private static int[] aux; // Reusable buffer

    public static void sort(int[] arr) {
        aux = new int[arr.length];
        metrics.reset();
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int lo, int hi) {
        if (hi - lo <= 7) { // Small-n cut-off
            insertionSort(arr, lo, hi);
            return;
        }
        metrics.increaseDepth();
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            sort(arr, lo, mid);
            sort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);
        }
        metrics.decreaseDepth();
    }

    private static void insertionSort(int[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= lo && arr[j] > key) {
                metrics.incrementComparisons(1);
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void merge(int[] arr, int lo, int mid, int hi) {
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (aux[j] < aux[i]) arr[k] = aux[j++];
            else arr[k] = aux[i++];
            metrics.incrementComparisons(1);
            metrics.incrementSwaps(1);
        }
    }

    // For testing
    public static int getMaxDepth() { return metrics.getMaxDepth(); }
    public static int getComparisonCount() { return metrics.getComparisonCount(); }
    public static int getSwapCount() { return metrics.getSwapCount(); }
}