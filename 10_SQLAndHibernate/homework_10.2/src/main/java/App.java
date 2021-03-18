import entity.Course;
import service.CourseService;
import utils.HibernateUtil;

public class App {
    private static final Integer COURSE_ID = 1;

    public static void main(String[] args) {
        CourseService courseService = new CourseService();
        Course course = courseService.getById(COURSE_ID);
        String courseName = course.getName();
        Integer countStudents = course.getStudentsCount();
        System.out.printf("Курс: \"%s\", количество студентов: \"%s\"\n",
                courseName, countStudents);
        HibernateUtil.shutdown();
    }
}