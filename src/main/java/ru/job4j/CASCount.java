package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount() {
        count.getAndSet(0);
    }

    public void increment() {
        this.count.compareAndSet(get(), get() + 1);
    }

    public int get() {
        return count.get();
    }
}