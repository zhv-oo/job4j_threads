package ru.job4j.pool;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;

public class ParallelIndexSearchTest {

    @Test
    public void whenSearchIndex1() {
        String[] array = new String[1];
        String chk = "search";
        array[0] = chk;
        ParallelIndexSearch<String> parallelIndexSearch = new ParallelIndexSearch<>(array, chk, 0, 1);
        Assert.assertThat(parallelIndexSearch.search(), is(0));
    }

    @Test
    public void whenSearchIndex6From10() {
        String[] array = new String[10];
        Arrays.fill(array, "newString");
        String chk = "search";
        array[6] = chk;
        ParallelIndexSearch<String> parallelIndexSearch = new ParallelIndexSearch<>(array, chk, 0, 10);
        Assert.assertThat(parallelIndexSearch.search(), is(6));
    }

    @Test
    public void whenSearchIndex9From10() {
        String[] array = new String[10];
        Arrays.fill(array, "newString");
        String chk = "search";
        array[9] = chk;
        ParallelIndexSearch<String> parallelIndexSearch = new ParallelIndexSearch<>(array, chk, 0, 10);
        Assert.assertThat(parallelIndexSearch.search(), is(9));
    }

    @Test
    public void whenSearchIndex20From21() {
        String[] array = new String[21];
        Arrays.fill(array, "newString");
        String chk = "search";
        array[20] = chk;
        ParallelIndexSearch<String> parallelIndexSearch = new ParallelIndexSearch<>(array, chk, 0, 21);
        Assert.assertThat(parallelIndexSearch.search(), is(20));
    }

    @Test
    public void whenSearchIndex17From30() {
        String[] array = new String[30];
        Arrays.fill(array, "newString");
        String chk = "search";
        array[17] = chk;
        ParallelIndexSearch<String> parallelIndexSearch = new ParallelIndexSearch<>(array, chk, 0, 30);
        Assert.assertThat(parallelIndexSearch.search(), is(17));
    }

    @Test
    public void whenSearchIndex0From33() {
        String[] array = new String[33];
        Arrays.fill(array, "newString");
        String chk = "search";
        array[0] = chk;
        ParallelIndexSearch<String> parallelIndexSearch = new ParallelIndexSearch<>(array, chk, 0, 33);
        Assert.assertThat(parallelIndexSearch.search(), is(0));
    }

    @Test
    public void whenSearchIndexMinus1From39() {
        String[] array = new String[39];
        Arrays.fill(array, "newString");
        String chk = "search";
        ParallelIndexSearch<String> parallelIndexSearch = new ParallelIndexSearch<>(array, chk, 0, 39);
        Assert.assertThat(parallelIndexSearch.search(), is(-1));
    }
}