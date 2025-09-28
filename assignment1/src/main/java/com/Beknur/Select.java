package com.Beknur;

public class Select {
    public static int select(int[] a, int k, Metrics metrics) {
        return select(a, 0, a.length - 1, k, metrics);
    }

    private static int select(int[] a, int lo, int hi, int k, Metrics metrics) {
        metrics.incrementDepth();
        if (lo == hi) {
            metrics.decrementDepth();
            return a[lo];
        }
        int pivot = medianOfMedians(a, lo, hi, metrics);
        int p = Util.partition(a, lo, hi, pivot, metrics); // Use util partition
        if (k == p) {
            metrics.decrementDepth();
            return a[p];
        } else if (k < p) {
            return select(a, lo, p - 1, k, metrics); // Smaller side if possible
        } else {
            return select(a, p + 1, hi, k, metrics);
        }
    }

    private static int medianOfMedians(int[] a, int lo, int hi, Metrics metrics) {
        int n = hi - lo + 1;
        if (n <= 5) return medianOfGroup(a, lo, hi, metrics);
        int groups = (n + 4) / 5;
        for (int i = 0; i < groups; i++) {
            int groupLo = lo + i * 5;
            int groupHi = Math.min(groupLo + 4, hi);
            int med = medianOfGroup(a, groupLo, groupHi, metrics);
            Util.swap(a, lo + i, med); // Move medians to front
        }
        return select(a, lo, lo + groups - 1, (groups - 1) / 2, metrics); // Recurse on medians
    }

    private static int medianOfGroup(int[] a, int lo, int hi, Metrics metrics) {
        // Simple sort for small group
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo; j--) {
                metrics.incrementComparisons();
                if (a[j] < a[j - 1]) Util.swap(a, j, j - 1);
            }
        }
        return (lo + hi) / 2;
    }
}