package ru.job4j.thread;
/**
 * Класс работы с монитором и критической секцией.
 */
public class Buffer {
    private StringBuilder buffer = new StringBuilder();

    public synchronized void add(int value) {
        System.out.print(value);
        buffer.append(value);
    }

    @Override
    public synchronized String toString() {
        return buffer.toString();
    }
}