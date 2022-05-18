package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Класс работы с ForkJoinPool разделения задачи на потоки выполнения.
 * @param <T> тип объектов массива.
 */
public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {
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
        if (to <= 10 || from != 0) {
            return new Searcher<T>().searchIndex(this.array, this.obj, this.from, this.to);
        }
        int mid = (from + to) / 2;
        if (to <= mid || to == array.length) {
            return new Searcher<T>().searchIndex(this.array, this.obj, this.from, this.to);
        }
        ParallelIndexSearch<T> leftSearch = new ParallelIndexSearch<>(array, obj, 0, mid);
        ParallelIndexSearch<T> rightSearch = new ParallelIndexSearch<>(array, obj, mid + 1, array.length);
        leftSearch.fork();
        rightSearch.fork();
        int left = leftSearch.join();
        int right = rightSearch.join();
        return left + right;
    }

    public int search() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<>(array, obj, 0, array.length));
    }

    private static class Searcher<T> {
        private int searchIndex(T[] array, T obj, int from, int to) {
            int out = 0;
            for (int i = from; i < to; i++) {
                if (array[i].equals(obj)) {
                    out = i;
                    break;
                }
            }
            return out;
        }
    }
}