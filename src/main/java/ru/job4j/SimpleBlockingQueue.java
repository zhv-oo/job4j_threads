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

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void offer(T value) {
        if (queue.size() == size) {
            check();
        }
        queue.offer(value);
        this.notifyAll();
    }

    public synchronized T poll() {
        T rsl;
        if (queue.size() == 0) {
            check();
        }
        rsl = queue.poll();
        this.notifyAll();
        return rsl;
    }

    private synchronized void check() {
        while (queue.size() == size || queue.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}