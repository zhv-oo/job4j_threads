package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Класс работы с ForkJoinPool разделения задачи на потоки выполнения.
 * @param <T> тип объектов массива.
 */
public class  ParallelIndexSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T obj;
    private final int from;
    private final int to;

    public ParallelIndexSearch(T[] array, T obj, int from, int to) {
        this.array = array;
        this.obj = obj;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if ((to - from) <= 10 || to == array.length) {
            return searchIndex();
        }
        int mid = array.length / 2;
        ParallelIndexSearch<T> leftSearch = new ParallelIndexSearch<>(array, obj, 0, mid);
        ParallelIndexSearch<T> rightSearch = new ParallelIndexSearch<>(array, obj, mid + 1, array.length);
        leftSearch.fork();
        rightSearch.fork();
        int left = leftSearch.join();
        int right = rightSearch.join();
        return Math.max(left, right);
    }

    public int search() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<>(array, obj, 0, array.length));
    }

    private int searchIndex() {
        int out = -1;
        for (int i = from; i < to; i++) {
            if (array[i].equals(obj)) {
                out = i;
                break;
            }
        }
        return out;
    }
}