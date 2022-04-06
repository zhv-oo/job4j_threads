package ru.job4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {
    @Test
    public void add() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(2);
        Thread first = new Thread(() -> simpleBlockingQueue.offer(1));
        Thread second = new Thread(() -> simpleBlockingQueue.offer(2));
        Thread third = new Thread(() -> simpleBlockingQueue.offer(3));
        Thread poll = new Thread(simpleBlockingQueue::poll);
        first.start();
        second.start();
        third.start();
        poll.start();
        first.join();
        second.join();
        third.join();
        poll.join();
        assertThat(simpleBlockingQueue.poll(), is(2));
    }

}