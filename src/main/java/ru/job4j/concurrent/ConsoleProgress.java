package ru.job4j.concurrent;

import java.util.List;

/**
 * Класс эмулирует работу загрузки.
 */

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        List<Character> process = List.of('\\', '|', '/');
        Thread thread = new Thread(
                () -> {
                    try {
                        int i = 0;
                        while (!Thread.currentThread().isInterrupted()) {
                            Thread.sleep(500);
                            System.out.print("\r load: " + process.get(i++));
                            if (i == 3) {
                                i = 0;
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }
}