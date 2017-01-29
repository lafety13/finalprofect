package ua.booking.dao;

import java.util.List;

public abstract class AbstractDao<T> implements Dao<T> {
    private List<T> localDb;

    protected final void setLocalDb(List<T> db) {
        localDb = db;
    }

    @Override
    public boolean save(T obj) {
        return localDb.add(obj);
    }

    @Override
    public boolean update(T obj) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public T getById(int id) {
        return localDb.get(id);
    }

    @Override
    public List<T> getAll() {
        return localDb;
    }

    @Override
    public boolean isExist(T obj) {
        return localDb.contains(obj);
    }
}
