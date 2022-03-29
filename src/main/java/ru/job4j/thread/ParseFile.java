package ru.job4j.thread;
/**
 * Класс работы с монитором и критической секцией. Visibility.
 */
import java.io.File;
import java.io.IOException;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent(Predicate<Integer> filter) throws IOException {
        return new GetContent().getContent(filter, file);
    }

    public void saveContent(String content) throws IOException {
        new SaveContent().saveContent(content, file);
    }
}