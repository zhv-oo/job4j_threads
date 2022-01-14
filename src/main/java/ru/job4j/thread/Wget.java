package ru.job4j.thread;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

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
            int sec = 1;
            int time = 0;
            while ((bytesRead = load.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                try {
                    Thread.sleep(9);
                    time += 9;
                    if (time >= 1000) {
                        System.out.print("\r Loading: " + sec++ + " sec");
                        time = 0;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        System.out.println("URL: " + url);
        System.out.println("SPEED: " + speed);
        wget.start();
        wget.join();
    }
}