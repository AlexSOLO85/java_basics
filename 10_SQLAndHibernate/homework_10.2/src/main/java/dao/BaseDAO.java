package dao;

import java.util.List;

public interface BaseDAO<T> {
    void add(T entity);

    List<T> getAll();

    T getById(Integer id);

    void update(T entity);

    void delete(T entity);
}