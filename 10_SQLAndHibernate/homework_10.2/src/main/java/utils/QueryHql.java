package utils;

import entity.Course;
import entity.LinkedPurchaseList;
import entity.PurchaseList;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

@SuppressWarnings("unchecked")
public class QueryHql extends SessionService {
    public void getQuery() {
        openTransactionSession();
        try (Session session = getSession()) {
            String hql = "FROM " + Course.class.getSimpleName() + " WHERE price > 100000";
            List<Course> list = session.createQuery(hql).setMaxResults(5).getResultList();
            closeTransactionSession();
            for (Course course : list) {
                System.out.println(course.getName() + " " + course.getPrice());
            }
        }
    }

    public void createAndFillTable() {
        openTransactionSession();
        Session session = getSession();
        List<PurchaseList> resultList = session.createQuery("FROM entity.PurchaseList").getResultList();
        for (PurchaseList list : resultList) {

            DetachedCriteria studentsCriteria = DetachedCriteria.forClass(Student.class)
                    .add(Restrictions.eq("name", list.getStudentName()));
            Student student = (Student) studentsCriteria.getExecutableCriteria(session).list().stream()
                    .findFirst().get();

            DetachedCriteria coursesCriteria = DetachedCriteria.forClass(Course.class)
                    .add(Restrictions.eq("name", list.getCourseName()));
            Course course = (Course) coursesCriteria.getExecutableCriteria(session).list().stream()
                    .findFirst().get();

            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList(
                    new LinkedPurchaseList.Id(student.getId(), course.getId()), student, course,
                    list.getPrice(), list.getSubscriptionDate());
            session.save(linkedPurchaseList);
        }
        closeTransactionSession();
    }
}