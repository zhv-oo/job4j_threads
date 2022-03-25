package ru.job4j.thread;
/**
 * Класс без общих ресурсов при использовании кэша.
 */

import net.jcip.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@NotThreadSafe
public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }
    /**
     * Получаем List с копией элементов.
     */
    public List<User> findAll() {
        List<User> tmpList = new ArrayList<>();
        users.values().forEach(u -> tmpList.add(User.of(u.getName())));
        return tmpList;
    }
}