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
        return store.putIfAbsent(user.getId(), user) == null;
    }
    public synchronized boolean update(User user) {
        return store.replace(user.getId(), user) == null;
    }

    public synchronized boolean delete(User user) {
        return store.remove(user.getId()) == null;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean res = false;
        User sendUser = store.get(fromId);
        User recUser = store.get(toId);
        if (sendUser != null && recUser != null
                    && sendUser.getAmount() >= amount) {
            sendUser.setAmount(sendUser.getAmount() - amount);
            recUser.setAmount(recUser.getAmount() + amount);
            res = true;
        }
        return res;
    }
}