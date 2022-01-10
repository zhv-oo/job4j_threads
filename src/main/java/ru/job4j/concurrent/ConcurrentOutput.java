package ru.job4j.concurrent;

/**
 * Проверка вывода имени нитей и их последовательности.g
 */
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())

        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())

        );
        another.start();
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}