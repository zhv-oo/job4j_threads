package ru.job4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void whenIncrement() {
        CASCount count = new CASCount();
        count.increment();
        assertThat(count.get(), is(1));
    }

    @Test
    public void whenIncrementThreeTime() {
        CASCount count = new CASCount();
        count.increment();
        count.increment();
        count.increment();
        assertThat(count.get(), is(3));
    }
}