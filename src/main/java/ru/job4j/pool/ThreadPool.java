package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size * 2);
    private boolean stop = false;

    public synchronized void run() {
        for (int i = 0; i < size; i++) {
            if (threads.get(i) != null && !threads.get(i).isInterrupted()) {
                threads.add(new Thread(() -> {
                    try {
                        if (!tasks.isEmpty()) {
                            tasks.poll().run();
                        } else {
                            Thread.currentThread().wait();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }));
            }
        }
        while (!stop) {
            threads.forEach(Thread::start);
        }
    }

    public synchronized void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public synchronized void shutdown() {
        threads.forEach(t -> {
            if (!t.isInterrupted()) {
                t.interrupt();
            }
        });
        this.stop = true;
    }
}