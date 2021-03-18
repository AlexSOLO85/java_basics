package dao;

import entity.Course;

import java.util.List;

public interface CourseDAO {
    void add(Course course);

    List<Course> getAll();

    Course getById(Integer id);

    void update(Course course);

    void delete(Course course);
}