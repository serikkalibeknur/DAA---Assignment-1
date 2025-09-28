package com.Beknur;

public class MergeSort {
    public static void sort(int[] a, Metrics metrics) {
        int[] aux = new int[a.length]; // Reusable buffer
        metrics.incrementAllocations(); // Count this
        sort(a, aux, 0, a.length - 1, metrics);
    }

    private static void sort(int[] a, int[] aux, int lo, int hi, Metrics metrics) {
        metrics.incrementDepth();
        if (hi - lo < 16) { // Cut-off
            insertionSort(a, lo, hi, metrics);
            metrics.decrementDepth();
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, metrics);
        sort(a, aux, mid + 1, hi, metrics);
        merge(a, aux, lo, mid, hi, metrics);
        metrics.decrementDepth();
    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi, Metrics metrics) {
        for (int k = lo; k <= hi; k++) aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j] < aux[i]) { a[k] = aux[j++]; metrics.incrementComparisons(); }
            else { a[k] = aux[i++]; metrics.incrementComparisons(); }
        }
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics metrics) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo && a[j] < a[j - 1]; j--) {
                int temp = a[j]; a[j] = a[j - 1]; a[j - 1] = temp;
                metrics.incrementComparisons();
            }
        }
    }
}