package ru.job4j.concurrent;

import java.util.List;

/**
 * Класс эмулирует работу загрузки.
 */

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        List<Character> process = List.of('\\', '|', '/');
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r load: " + process.get(i++));
            if (i == process.size()) {
                i = 0;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }
}