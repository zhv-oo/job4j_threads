package ru.job4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {
    @Test
    public void add() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(2);
        Thread offerTread = new Thread(() -> {
            simpleBlockingQueue.offer(1);
            simpleBlockingQueue.offer(2);
            simpleBlockingQueue.offer(3);

        });
        Thread pollTread = new Thread(simpleBlockingQueue::poll);
        offerTread.start();
        pollTread.start();
        offerTread.join();
        pollTread.join();
        assertThat(simpleBlockingQueue.poll(), is(2));
    }
}