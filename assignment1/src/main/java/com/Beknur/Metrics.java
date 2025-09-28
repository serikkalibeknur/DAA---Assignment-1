package com.Beknur;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Metrics {
    private long startTime;
    private AtomicInteger depth = new AtomicInteger(0);
    private AtomicInteger comparisons = new AtomicInteger(0);
    private AtomicInteger allocations = new AtomicInteger(0);

    public void start() {
        startTime = System.nanoTime();
        depth.set(0);
        comparisons.set(0);
        allocations.set(0);
    }

    public void incrementDepth() { depth.incrementAndGet(); }
    public void decrementDepth() { depth.decrementAndGet(); }
    public void incrementComparisons() { comparisons.incrementAndGet(); }
    public void incrementAllocations() { allocations.incrementAndGet(); }

    public long getTimeNs() { return System.nanoTime() - startTime; }
    public int getDepth() { return depth.get(); }
    public int getComparisons() { return comparisons.get(); }
    public int getAllocations() { return allocations.get(); }

    public void writeToCSV(String fileName, int n, String algo) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.append(algo).append(",").append(String.valueOf(n)).append(",")
                    .append(String.valueOf(getTimeNs())).append(",")
                    .append(String.valueOf(getDepth())).append(",")
                    .append(String.valueOf(getComparisons())).append(",")
                    .append(String.valueOf(getAllocations())).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}