package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Реализуйте шаблон Producer Consumer.
 * @param <T> очередь объектов.
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    private final int size;
    private int count = 0;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void offer(T value) {
        if (count != 0) {
            check();
        }
        count++;
        queue.offer(value);
        this.notifyAll();
    }

    public synchronized T poll() {
        if (count != size) {
            check();
        }
        count--;
        this.notifyAll();
        return queue.poll();
    }

    private synchronized void check() {
        while (count == size) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}