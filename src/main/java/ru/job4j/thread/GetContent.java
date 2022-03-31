package ru.job4j.thread;
/**
 * Класс получение контента из файла.
 */
import java.io.*;
import java.util.function.Predicate;

public class GetContent {
    public synchronized String getContent(Predicate<Integer> filter, File file) {
        StringBuilder str = new StringBuilder();
        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = reader.read()) > 0) {
                if (filter.test(data)) {
                    str.append(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}