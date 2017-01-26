package ua.booking.dao;

import java.util.List;

public interface Dao<T> {
    boolean save(T obj);

    boolean update(T obj);

    boolean deleteById(int id);

    T getById(int id);

    List<T> getAll();

    boolean isExist(T obj);

}
