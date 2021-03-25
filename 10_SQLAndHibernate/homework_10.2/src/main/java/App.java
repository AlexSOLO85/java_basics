import entity.Course;
import entity.Student;
import entity.Teacher;
import service.CourseService;
import service.StudentService;
import service.TeacherService;
import utils.HibernateSessionFactory;
import utils.QueryHql;

public class App {
    private static final Integer COURSE_ID = 1;
    private static final Integer STUDENT_ID = 1;
    private static final Integer TEACHER_ID = 1;

    public static void main(String[] args) {
        CourseService courseService = new CourseService(Course.class);
        StudentService studentService = new StudentService(Student.class);
        TeacherService teacherService = new TeacherService(Teacher.class);

        Course course = courseService.getById(COURSE_ID);
        Student student = studentService.getById(STUDENT_ID);
        Teacher teacher = teacherService.getById(TEACHER_ID);

        String courseName = course.getName();
        Integer countStudents = course.getStudentsCount();
        System.out.printf("Курс: \"%s\", количество студентов: \"%s\"\n",
                courseName, countStudents);

        String studentName = student.getName();
        Integer ageStudent = student.getAge();
        System.out.printf("Студент: \"%s\", возраст: \"%s\"\n",
                studentName, ageStudent);

        String teacherName = teacher.getName();
        Integer ageTeacher = teacher.getAge();
        System.out.printf("Преподаватель: \"%s\", возраст: \"%s\"\n",
                teacherName, ageTeacher);

        QueryHql queryHql = new QueryHql();
        queryHql.getQuery();
        queryHql.createAndFillTable();

        HibernateSessionFactory.shutdown();
    }
}