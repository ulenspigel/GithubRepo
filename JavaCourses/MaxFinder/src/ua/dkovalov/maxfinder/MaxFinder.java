package ua.dkovalov.maxfinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.*;

public class MaxFinder {
    static final int ITEMS_PER_WORKER = 1000;
    static final int NUM_OF_CPU = Runtime.getRuntime().availableProcessors();

    public static <E extends Comparable<E>> E calculate(List<E> list) {
        if (list.size() == 0) {
            return null;
        }

        int numOfWorkers = Math.min(NUM_OF_CPU, (int) Math.ceil(list.size() / (float) ITEMS_PER_WORKER));
        List<Future<E>> subResults = new ArrayList<>(numOfWorkers);
        ExecutorService executor = Executors.newFixedThreadPool(numOfWorkers);
        for (int i = 0; i < numOfWorkers; i++) {
            int leftBound = i * list.size() / numOfWorkers;
            int rightBound = (i + 1) * list.size() / numOfWorkers;
            subResults.add(executor.submit(new Worker<>(list.subList(leftBound, rightBound))));
        }
        executor.shutdown();

        // TODO: find a way to reuse Worker class
        E max = null;
        try {
            max = subResults.get(0).get();
            for (int i = 1; i < subResults.size(); i++) {
                E subResult = subResults.get(i).get();
                if (subResult.compareTo(max) == 1) {
                    max = subResult;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return max;
    }

    private static class Worker<T extends Comparable<T>> implements Callable<T> {
        List<T> list;

        public Worker(List<T> list) {
            this.list = list;
        }

        @Override
        public T call() {
            if (list.size() == 0) {
                return null;
            }
            T max = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                T item = list.get(i);
                if (item.compareTo(max) == 1) {
                    max = item;
                }
            }
            //System.out.println(Thread.currentThread() + " (" + list + "): " + max);
            return max;
        }
    }
}
