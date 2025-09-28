package com.Beknur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {
    @BeforeEach
    public void setUp() {
        MergeSort.metrics.reset();
    }

    @Test
    public void testSortRandomArray() {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        int[] expected = arr.clone();
        Arrays.sort(expected);
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testSortAdversarialArray() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) arr[i] = arr.length - i;
        MergeSort.sort(arr);
        assertTrue(isSorted(arr));
    }

    private boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < arr[i - 1]) return false;
        return true;
    }
}