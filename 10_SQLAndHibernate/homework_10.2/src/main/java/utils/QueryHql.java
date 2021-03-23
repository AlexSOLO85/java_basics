package utils;

import entity.Course;
import org.hibernate.Session;

import java.util.List;

@SuppressWarnings("unchecked")
public class QueryHql extends SessionService {
    public void getQuery() {
        openTransactionSession();
        try (Session session = getSession()){
            String hql = "FROM " + Course.class.getSimpleName() + " WHERE price > 100000";
            List<Course> list = session.createQuery(hql).setMaxResults(5).getResultList();
            closeTransactionSession();
            for (Course course : list) {
                System.out.println(course.getName() + " " + course.getPrice());
            }
        }
    }
}