import entity.Course;
import entity.CourseType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import service.CourseService;
import utils.HibernateSessionFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HibernateTest {
    private static SessionFactory sessionFactory;
    private Session session;

    private static final String NAME = "Новый курс";
    private static final CourseType TYPE = CourseType.PROGRAMMING;
    private static final Integer PRICE = 20000;
    private static final Integer NEW_PRICE = 30000;
    private static final String SQL = "SELECT MAX(courses.id) FROM courses";

    CourseService courseService = new CourseService();
    Course course = new Course();

    @BeforeAll
    public static void setup() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
        System.out.println("SessionFactory created");
    }

    @AfterAll
    public static void tear_down() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @DisplayName("Создание записи")
    @Order(1)
    @Test
    public void test_create() {
        System.out.println("Running test_create");
        course.setName(NAME);
        course.setType(TYPE);
        course.setPrice(PRICE);
        courseService.add(course);
        Assertions.assertTrue(course.getId() > 0);
    }

    @DisplayName("Обновление записи")
    @Order(2)
    @Test
    public void test_update() {
        System.out.println("Running test_update");
        Integer id = (Integer) session.createSQLQuery(SQL).uniqueResult();
        Course course = session.find(Course.class, id);
        course.setName(NAME);
        course.setType(TYPE);
        course.setPrice(NEW_PRICE);
        courseService.update(course);
        assertEquals("Новый курс", course.getName());
    }

    @DisplayName("Получение записи")
    @Order(3)
    @Test
    public void test_get() {
        System.out.println("Running test_get");
        Integer id = (Integer) session.createSQLQuery(SQL).uniqueResult();
        Course course = session.find(Course.class, id);
        assertEquals("Новый курс", course.getName());
    }

    @DisplayName("Получение списка")
    @Order(4)
    @Test
    public void test_list() {
        System.out.println("Running test_list");
        List<Course> resultList = courseService.getAll();
        Assertions.assertFalse(resultList.isEmpty());
    }

    @DisplayName("Удаление записи")
    @Order(5)
    @Test
    public void test_delete() {
        System.out.println("Running test_delete");
        Integer id = (Integer) session.createSQLQuery(SQL).uniqueResult();
        Course course = session.find(Course.class, id);
        courseService.delete(course);
        Assertions.assertNull(null);
    }

    @BeforeEach
    public void open_session() {
        session = sessionFactory.openSession();
        System.out.println("Session created");
    }

    @AfterEach
    public void close_session() {
        if (session != null) session.close();
        System.out.println("Session closed\n");
    }
}