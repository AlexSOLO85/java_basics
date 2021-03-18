package service;

import dao.CourseDAO;
import entity.Course;
import org.hibernate.Session;
import utils.SessionUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CourseService extends SessionUtil implements CourseDAO {
    private final String COLUMN_ID = "id";

    @Override
    public void add(Course course) {
        openTransactionSession();
        Session session = getSession();
        session.save(course);
        closeTransactionSession();
    }

    @Override
    public List<Course> getAll() {
        openTransactionSession();
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root);
        List<Course> courseList = session.createQuery(query).getResultList();
        closeTransactionSession();
        return courseList;
    }

    @Override
    public Course getById(Integer id) {
        openTransactionSession();
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root).where(builder.equal(root.get(COLUMN_ID), id));
        Course course = session.createQuery(query).getSingleResult();
        closeTransactionSession();
        return course;
    }

    @Override
    public void update(Course course) {
        openTransactionSession();
        Session session = getSession();
        session.update(course);
        closeTransactionSession();
    }

    @Override
    public void delete(Course course) {
        openTransactionSession();
        Session session = getSession();
        session.remove(course);
        closeTransactionSession();
    }
}