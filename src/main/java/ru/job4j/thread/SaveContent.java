package ru.job4j.thread;
/**
 * Класс сохранение контента в файл.
 */
import java.io.*;

public class SaveContent {

    public synchronized void saveContent(String content, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()))) {
            for (int i = 0; i < content.length(); i += 1) {
                writer.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
