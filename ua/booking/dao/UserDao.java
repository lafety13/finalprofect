package ua.booking.dao;

import ua.booking.entities.User;

import java.util.LinkedList;
import java.util.List;

public class UserDao<T extends User> extends AbstractDao<T> implements Dao<T> {
    private List<T> userDb = new LinkedList<>();

    {
        super.setLocalDb(userDb);
    }

    @Override
    public boolean save(T obj) {
        return super.save(obj);
    }

    @Override
    public boolean update(T obj) {
        return super.update(obj);
    }

    @Override
    public boolean deleteById(int id) {
        return super.deleteById(id);
    }

    @Override
    public T getById(int id) {
        return super.getById(id);
    }

    @Override
    public List<T> getAll() {
        return super.getAll();
    }

    @Override
    public boolean isExist(T obj) {
        return super.isExist(obj);
    }
}
