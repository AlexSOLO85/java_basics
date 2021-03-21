package service;

import dao.CourseDAO;
import entity.Course;
import org.hibernate.Session;
import utils.SessionService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CourseService extends SessionService implements CourseDAO {
    private final String COLUMN_ID = "id";

    @Override
    public void add(Course course) {
        openTransactionSession();
        try (Session session = getSession()) {
            session.save(course);
            closeTransactionSession();
        }
    }

    @Override
    public List<Course> getAll() {
        openTransactionSession();
        try (Session session = getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Course> query = builder.createQuery(Course.class);
            Root<Course> root = query.from(Course.class);
            query.select(root);
            closeTransactionSession();
            return session.createQuery(query).getResultList();
        }
    }

    @Override
    public Course getById(Integer id) {
        openTransactionSession();
        try (Session session = getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Course> query = builder.createQuery(Course.class);
            Root<Course> root = query.from(Course.class);
            query.select(root).where(builder.equal(root.get(COLUMN_ID), id));
            closeTransactionSession();
            return session.createQuery(query).getSingleResult();
        }
    }

    @Override
    public void update(Course course) {
        openTransactionSession();
        try (Session session = getSession()) {
            session.update(course);
            closeTransactionSession();
        }
    }

    @Override
    public void delete(Course course) {
        openTransactionSession();
        try (Session session = getSession()) {
            session.remove(course);
            closeTransactionSession();
        }
    }
}