package ru.job4j.thread;
/**
 * Класс сохранение контента в файл.
 */
import java.io.*;

public class SaveContent {

    public synchronized void saveContent(String content, File file) {
        try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i += 1) {
                writer.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}