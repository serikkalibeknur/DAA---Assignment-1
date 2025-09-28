package com.Beknur;

import java.util.Arrays;

public class MergeSort {
    private static Metrics metrics = new Metrics();
    private static int[] aux; // Reusable buffer

    public static void sort(int[] arr) {
        aux = new int[arr.length]; // Single allocation
        metrics.incrementAllocations(1);
        metrics.reset();
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int lo, int hi) {
        if (hi - lo <= 7) { // Small-n cut-off
            insertionSort(arr, lo, hi);
            return;
        }
        metrics.incrementDepth();
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            sort(arr, lo, mid);
            sort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);
        }
        metrics.decrementDepth();
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
            if (i > mid) {
                metrics.incrementComparisons(1);
                arr[k] = aux[j++];
            } else if (j > hi) {
                metrics.incrementComparisons(1);
                arr[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                metrics.incrementComparisons(1);
                arr[k] = aux[j++];
            } else {
                metrics.incrementComparisons(1);
                arr[k] = aux[i++];
            }
            metrics.incrementSwaps(1);
        }
    }

    public static int getMaxDepth() { return metrics.getMaxDepth(); }
    public static int getComparisonCount() { return metrics.getComparisonCount(); }
    public static int getSwapCount() { return metrics.getSwapCount(); }
}