package com.lanmo.dao;

import com.lanmo.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findUserByUserName(String userName);
    User findByUserName(String userName);
    User findOne1(long id);
    User findOne2(long id);
    User save(User user);
    long count();
    List<User> findAll();
}
