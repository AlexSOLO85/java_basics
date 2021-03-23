package service;

import dao.BaseDAO;
import org.hibernate.Session;
import utils.SessionService;

import java.util.List;

public class BaseService<T> extends SessionService implements BaseDAO<T> {
    private final Class<T> clazz;

    public BaseService(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void add(T entity) {
        openTransactionSession();
        try (Session session = getSession()) {
            session.save(entity);
            closeTransactionSession();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        openTransactionSession();
        try (Session session = getSession()) {
            return session.createQuery("FROM " + clazz.getName()).list();
        }
    }

    public T getById(Integer id) {
        openTransactionSession();
        try (Session session = getSession()) {
            closeTransactionSession();
            return session.find(clazz ,id);
        }
    }

    @Override
    public void update(T entity) {
        openTransactionSession();
        try (Session session = getSession()) {
            session.update(entity);
            closeTransactionSession();
        }
    }

    @Override
    public void delete(T entity) {
        openTransactionSession();
        try (Session session = getSession()) {
            session.remove(entity);
            closeTransactionSession();
        }
    }
}