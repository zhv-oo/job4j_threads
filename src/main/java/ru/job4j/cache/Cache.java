package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) throws OptimisticException {
        int modelVer = model.getVersion();
        int memVersion = memory.get(model.getId()).getVersion();
        if (modelVer != memVersion) {
            throw new OptimisticException("Versions is not valid");
        }
        return memory.computeIfPresent(
                modelVer,
                (m, b) -> m == memVersion ? b : model) == null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Base get(Integer id) {
        return memory.get(id);
    }
}