package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Класс который запускает поток скачивания с ограничением скорости.
 */
public class Wget implements Runnable {
    /**
     * URL скачиваемого файла.
     */
    private final String url;
    /**
     * Скорость скачивания в байтах.
     */
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream load = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("loadedFile")) {
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            Long startTime = System.currentTimeMillis();
            int sec = 0;
            while ((bytesRead = load.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                try {
                    System.out.print("\r Loading: " + sec++ + " sec");
                    Long inTime = System.currentTimeMillis();
                    Thread.sleep(1000 - (inTime - startTime));
                    startTime = inTime + 1000;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            if (args[0].isEmpty()) {
                System.out.println("Ввдеите ссылку на файл первым аргументом при запуске программы!");
            }
            if (args[1].isEmpty()) {
                System.out.println("Ввдеите скорость скачивания в байтах вторым аргументом при запуске программы!");
            }
            String url = args[0];
            int speed = Integer.parseInt(args[1]);
            Thread wget = new Thread(new Wget(url, speed));
            System.out.println("URL: " + url);
            System.out.println("SPEED: " + speed + " byte");
            wget.start();
            wget.join();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ввдеите необходимые аргументы 1.Ссылка на файл, 2.Скорость скаивания в байтах!");
            System.out.println("Пример wget http://somefile 1024");
        }
    }
}