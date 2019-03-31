package com.lanmo.dao.impl;

import com.lanmo.dao.UserDao;
import com.lanmo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private static String SELECT_USER_BY_ID = "select * from sys_user where id = ?";

    @Autowired(required = true)
    private JdbcOperations jdbcOperations;

    @Inject
    public UserDaoImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public List<User> findUserByUserName(String userName){
        return new ArrayList<>();
    }

    public User findByUserName(String userName){
        return new User(userName);
    }

    public User findOne(long id){
        return jdbcOperations.queryForObject(SELECT_USER_BY_ID,new UserMapper(),id);
    }

    public User findOne1(long id){
        return jdbcOperations.queryForObject(SELECT_USER_BY_ID, (rs, rowNum) -> {
            return   new User(rs.getString("username"),rs.getString("password"));
        }, id);
    }

    public User findOne2(long id){
        return jdbcOperations.queryForObject(SELECT_USER_BY_ID, this::mapUser,id);
    }

    private User mapUser(ResultSet rs, int row ) throws SQLException {
        return new User(rs.getString("username"), rs.getString("password"));
    }

    private static final class UserMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getString("username"), rs.getString("password"));
        }
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
