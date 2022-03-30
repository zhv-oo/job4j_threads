package ru.job4j.thread;
/**
 * Класс получение контента из файла.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

public class GetContent {
    public synchronized String getContent(Predicate<Integer> filter, File file) throws IOException {
        InputStream i = new FileInputStream(file);
        StringBuilder str = new StringBuilder();
        int data;
        while ((data = i.read()) > 0) {
            if (filter.test(data)) {
                str.append(data);
            }
        }
        return str.toString();
    }
}
