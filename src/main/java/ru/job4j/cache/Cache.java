package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(
                model.getId(), (id, mod) -> {
                    if (mod.getVersion() != model.getVersion()) {
                        throw new OptimisticException("Version is different!");
                    }
                    model.setVersion(model.getVersion() + 1);
                    return model;
                }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Base get(Integer id) {
        return memory.get(id);
    }
}