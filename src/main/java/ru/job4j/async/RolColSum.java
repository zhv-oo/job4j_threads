package ru.job4j.async;

import java.util.concurrent.CompletableFuture;

/**
 * Класс поточного и асинхронного подсчета суммы столбцов и строк.
 * CompletableFuture асинхронное выполнение.
 */

public class RolColSum {
    public static class Sums {
        private final int rowSum;
        private final int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sumsAr = new Sums[matrix.length];
        int i, j;
        for (i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (j = 0; j < matrix[i].length; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
            }
            sumsAr[i] = new Sums(rowSum, colSum);
        }
        return sumsAr;
    }

    public static Sums[] asyncSum(int[][] matrix)throws Exception {
        Sums[] sumsAr = new Sums[matrix.length];
        int i, j;
        for (i = 0; i < matrix.length; i++) {
            int colSum = 0;
            CompletableFuture<Integer> rowSum = sum(matrix[i]);
            for (j = 0; j < matrix[i].length; j++) {
                colSum += matrix[j][i];
            }
            sumsAr[i] = new Sums(rowSum.get(), colSum);
        }
        return sumsAr;
    }

    public static CompletableFuture<Integer> sum(int[] line) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int out = 0;
                    for (int num: line) {
                        out += num;
                    }
                    return out;
                }
        );
    }

}
