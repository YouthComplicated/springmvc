package com.lanmo.dao;

import com.lanmo.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findUserByUserName(String userName);
    User findByUserName(String userName);
}
