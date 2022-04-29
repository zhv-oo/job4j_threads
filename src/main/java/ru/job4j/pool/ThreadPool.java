package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public synchronized void work(Runnable job) {
        boolean out = false;
        while (!out) {
            for (int i = 0; i < size; i++) {
                try {
                    if (!threads.get(i).isInterrupted()) {
                        tasks.offer(job);
                        out = true;
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void shutdown() {
        threads.forEach(t -> {
            if (t.isInterrupted()) {
                t.interrupt();
            }
        });
    }
}