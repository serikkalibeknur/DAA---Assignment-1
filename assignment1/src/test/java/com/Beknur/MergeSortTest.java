package com.Beknur;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {
    @Test
    public void testSort() {
        int[] arr = {5, 3, 8, 4, 2};
        Metrics m = new Metrics();
        MergeSort.sort(arr, m);
        assertArrayEquals(new int[]{2, 3, 4, 5, 8}, arr);
    }
    // Add more: random arrays, adversarial (sorted/reverse), check depth < log n * 2
}