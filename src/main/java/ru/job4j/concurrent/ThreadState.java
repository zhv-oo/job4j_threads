package ru.job4j.concurrent;

/**
 * Метод наглядной работы с состояниями потоков.
 */
public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { });
        Thread second = new Thread(
                () -> { });
        System.out.println("First thread name: " + first.getName());
        System.out.println("Second thread name: " + second.getName());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println("First thread: " + first.getState());
            System.out.println("Second thread: " + second.getState());
        }
        System.out.println("name thread: " + Thread.currentThread().getName());
    }
}