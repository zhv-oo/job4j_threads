package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) throws OptimisticException {
        return memory.computeIfPresent(
                model.getId(),
                (id, mod) -> model.getVersion() == mod.getVersion() ? model.incVersion() : Cache.throwOptimisticException()) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Base get(Integer id) {
        return memory.get(id);
    }

    @SuppressWarnings("checkstyle:LeftCurly")
    private static <T> T throwOptimisticException() {
        throw new OptimisticException("Version in not valid");
    }
}