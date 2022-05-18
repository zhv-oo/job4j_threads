package ru.job4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExecutorService класс.
 */
public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        String email = user.getEmail();
        String userName = user.getUserName();
        String subject = "Notification " + userName + "to email " + email + ".";
        String body = "Add a new event to " + userName;
        pool.submit(() -> send(subject, body, email));
        close();
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void send(String subject, String body, String email) {
    }
}
