package ru.job4j.thread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStore {
    @GuardedBy("this")
    private final Map<Integer, User> store = new HashMap<>();

    public synchronized boolean add(User user) {
        boolean res = false;
        if (!store.containsKey(user.getId())) {
            store.put(user.getId(), user);
            res = true;
        }
        return res;
    }
    public synchronized boolean update(User user) {
        boolean res = false;
        if (store.containsKey(user.getId())) {
            store.put(user.getId(), user);
            res = true;
        }
        return res;
    }

    public synchronized boolean delete(User user) {
        boolean res = false;
        if (store.containsKey(user.getId())) {
            store.remove(user.getId());
            res = true;
        }
        return res;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean res = false;
        if (store.containsKey(fromId) && store.containsKey(toId)
                    && store.get(fromId).getAmount() >= amount) {
            store.get(fromId).sendAmount(amount);
            store.get(toId).takeAmount(amount);
            res = true;
        }
        return res;
    }
}