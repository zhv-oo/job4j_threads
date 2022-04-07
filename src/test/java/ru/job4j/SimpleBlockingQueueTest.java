package ru.job4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {
    @Test
    public void add() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(2);

        Thread offerTread = new Thread(() -> {
            try {
                simpleBlockingQueue.offer(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                simpleBlockingQueue.offer(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                simpleBlockingQueue.offer(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread pollTread = new Thread(() -> {
            try {
                simpleBlockingQueue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        offerTread.start();
        pollTread.start();
        offerTread.join();
        pollTread.join();
        assertThat(simpleBlockingQueue.poll(), is(2));
    }
}