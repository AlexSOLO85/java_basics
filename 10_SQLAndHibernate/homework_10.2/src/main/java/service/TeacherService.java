package service;

import entity.Teacher;

import java.util.List;

public class TeacherService extends BaseService<Teacher> {
    public TeacherService(Class<Teacher> clazz) {
        super(clazz);
    }

    @Override
    public void add(Teacher entity) {
        super.add(entity);
    }

    @Override
    public List<Teacher> getAll() {
        return super.getAll();
    }

    @Override
    public Teacher getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public void update(Teacher entity) {
        super.update(entity);
    }

    @Override
    public void delete(Teacher entity) {
        super.delete(entity);
    }
}
