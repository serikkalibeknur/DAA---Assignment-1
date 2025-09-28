package com.Beknur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SelectTest {
    @BeforeEach
    public void setUp() {
        DeterministicSelect.metrics.reset();
    }

    @Test
    public void testSelectKthSmallest() {
        int[] arr = {7, 10, 4, 3, 20, 15};
        for (int k = 1; k <= arr.length; k++) {
            int expected = java.util.Arrays.copyOf(arr, arr.length);
            java.util.Arrays.sort(expected);
            int actual = DeterministicSelect.select(arr.clone(), k - 1); // 0-based index
            assertEquals(expected[k - 1], actual);
        }
    }

    @Test
    public void testSelectVsArraysSort() {
        int trials = 100;
        int n = 1000;
        for (int t = 0; t < trials; t++) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = (int) (Math.random() * 1000);
            int k = (int) (Math.random() * n);
            int expected = java.util.Arrays.copyOf(arr, arr.length);
            java.util.Arrays.sort(expected);
            int actual = DeterministicSelect.select(arr.clone(), k);
            assertEquals(expected[k], actual);
        }
    }

    private boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < arr[i - 1]) return false;
        return true;
    }
}