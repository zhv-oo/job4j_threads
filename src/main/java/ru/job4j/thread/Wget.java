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
        String[] urls  = url.split("/");
        try (BufferedInputStream load = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(urls[urls.length - 1])) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int sec = 0;
            long bytesWrite = 0L;
            long deltaTime;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = load.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                bytesWrite += 1024;
                try {
                    if (bytesWrite >= speed) {
                        deltaTime = System.currentTimeMillis() - startTime;
                        if (deltaTime < 1000) {
                            Thread.sleep(1000 - deltaTime);
                        }
                        startTime = System.currentTimeMillis();
                        bytesWrite = 0;
                        System.out.print("\r Loading: " + sec++ + " sec");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Ввдеите необходимые аргументы 1.Ссылка на файл, 2.Скорость скаивания в байтах! "
                            + "Пример wget http://212.183.159.230/10MB.zip  1048576"
            );
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validArgs(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        System.out.println("URL: " + url);
        System.out.println("SPEED: " + speed + " byte");
        wget.start();
        wget.join();
    }
}