package ru.job4j.async;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class RolColSumTest {
    @Test
    public void whenLineSum() {
        int[][] matrix = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] out = RolColSum.sum(matrix);
        assertThat(out[1].getColSum(), is(15));
        assertThat(out[2].getRowSum(), is(24));
    }

    @Test
    public void whenAsyncSum() throws Exception {
        int[][] matrix = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] out = RolColSum.asyncSum(matrix);
        assertThat(out[1].getColSum(), is(15));
        assertThat(out[2].getRowSum(), is(24));
    }
}