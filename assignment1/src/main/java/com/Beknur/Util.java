package com.beknur;

public class Util {
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        Metrics.metrics.incrementSwaps();
    }

    public static int partition(int[] a, int lo, int hi, int pivot, Metrics m) {
        // Lomuto partition logic with comparisons
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            m.incrementComparisons();
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, hi);
        return i + 1;
    }

    public static void shuffle(int[] a) {
        Random rand = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(a, i, j);
        }
    }
}