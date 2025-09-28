package com.yourname;

import java.util.Random;

public class QuickSort {
    private static Random rand = new Random();

    public static void sort(int[] a, Metrics metrics) {
        shuffle(a); // Randomize to avoid worst-case
        sort(a, 0, a.length - 1, metrics);
    }

    private static void sort(int[] a, int lo, int hi, Metrics metrics) {
        while (lo < hi) {
            metrics.incrementDepth();
            int p = partition(a, lo, hi, metrics);
            if (p - lo < hi - p) { // Recurse smaller
                sort(a, lo, p - 1, metrics);
                lo = p + 1; // Iterate larger
            } else {
                sort(a, p + 1, hi, metrics);
                hi = p - 1;
            }
            metrics.decrementDepth();
        }
    }

    private static int partition(int[] a, int lo, int hi, Metrics metrics) {
        int pivot = a[lo + rand.nextInt(hi - lo + 1)]; // Randomized pivot
        int i = lo, j = hi + 1;
        while (true) {
            while (a[++i] < pivot) { metrics.incrementComparisons(); if (i == hi) break; }
            while (pivot < a[--j]) { metrics.incrementComparisons(); if (j == lo) break; }
            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    private static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(a, i, j);
        }
    }
}