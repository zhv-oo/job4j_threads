package ru.job4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CASCountTest {

    private static class ThreadCasCount extends Thread {
        private final CASCount count;

        private ThreadCasCount(final CASCount count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenIncrement() throws InterruptedException {
        final CASCount count = new CASCount();
        Thread first = new ThreadCasCount(count);
        Thread second = new ThreadCasCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(2));
    }

    @Test
    public void whenIncrementThreeTime() throws InterruptedException {
        final CASCount count = new CASCount();
        Thread first = new ThreadCasCount(count);
        Thread second = new ThreadCasCount(count);
        Thread three = new ThreadCasCount(count);
        first.start();
        second.start();
        three.start();
        first.join();
        second.join();
        three.join();
        assertThat(count.get(), is(3));
    }
}