package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public synchronized void work(Runnable job) {
        boolean out = false;
        while (!out) {
            for (int i = 0; i < size; i++) {
                if (threads.get(i) != null && !threads.get(i).isInterrupted()) {
                    threads.add(new Thread(
                            () -> {
                                try {
                                    tasks.offer(job);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }

                            }));
                    out = true;
                    break;
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