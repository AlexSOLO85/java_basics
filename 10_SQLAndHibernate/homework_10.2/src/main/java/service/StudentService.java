package service;

import entity.Student;

import java.util.List;

public class StudentService extends BaseService<Student> {
    public StudentService(Class<Student> clazz) {
        super(clazz);
    }

    @Override
    public void add(Student entity) {
        super.add(entity);
    }

    @Override
    public List<Student> getAll() {
        return super.getAll();
    }

    @Override
    public Student getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public void update(Student entity) {
        super.update(entity);
    }

    @Override
    public void delete(Student entity) {
        super.delete(entity);
    }
}