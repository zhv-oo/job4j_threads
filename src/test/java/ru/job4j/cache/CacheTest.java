package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void add() {
        Cache cache = new Cache();
        cache.add(new Base(1, 1));
        assertThat(cache.get(1).getId(), is(1));
    }

    @Test
    public void whenUpdateTrue() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        base.setName("newName");
        assertTrue(cache.update(base));
        assertThat(cache.get(1).getVersion(), is(2));
    }

    @Test
    public void whenUpdateFalse() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        cache.update(base);
        assertFalse(cache.update(base));
    }

    @Test
    public void delete() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        cache.delete(base);
        assertNull(cache.get(1));
    }
}