package com.lanmo.dao;

import com.lanmo.entity.Teacher;
import com.lanmo.entity.User;

import java.util.List;

public interface TeacherDao {
    List<Teacher> findUserByUserName(String userName);
    Teacher findByUserName(String userName);
    Teacher findOne1(Integer id);
    Teacher findOne2(Integer id);
    Teacher save(Teacher user);
    long count();
    List<Teacher> findAll();
    void insert(Teacher teacher);
}
