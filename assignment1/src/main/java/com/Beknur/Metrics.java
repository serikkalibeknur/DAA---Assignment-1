package com.Beknur;

import java.io.FileWriter;
import java.io.IOException;

public class Metrics {
    private int comparisonCount;
    private int swapCount;
    private int allocationCount;
    private int recursionDepth;
    private int maxDepth;

    public Metrics() {
        reset();
    }

    public void reset() {
        comparisonCount = 0;
        swapCount = 0;
        allocationCount = 0;
        recursionDepth = 0;
        maxDepth = 0;
    }

    public void incrementComparisons(int count) {
        comparisonCount += count;
    }

    public void incrementSwaps(int count) {
        swapCount += count;
    }

    public void incrementAllocations(int count) {
        allocationCount += count;
    }

    public void increaseDepth() {
        recursionDepth++;
        maxDepth = Math.max(maxDepth, recursionDepth);
    }

    public void decreaseDepth() {
        recursionDepth--;
    }

    public void writeToCSV(String filename, int size, long timeMs) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.append(String.format("%d,%d,%d,%d,%d%n", size, timeMs, comparisonCount, swapCount, maxDepth));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters for testing and reporting
    public int getComparisonCount() { return comparisonCount; }
    public int getSwapCount() { return swapCount; }
    public int getAllocationCount() { return allocationCount; }
    public int getMaxDepth() { return maxDepth; }
}