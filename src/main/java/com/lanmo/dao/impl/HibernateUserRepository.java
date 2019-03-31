package com.lanmo.dao.impl;

import com.lanmo.dao.UserDao;
import com.lanmo.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

//@Repository
public class HibernateUserRepository {

    private SessionFactory sessionFactory;

    @Inject
    public HibernateUserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     *（1）openSession每次打开都是新的Session，所以多次获取的Session实例是不同的，并且需要人为的调用close方法进行Session关闭。
     *（2）getCurrentSession是从当前上下文中获取Session并且会绑定到当前线程，第一次调用时会创建一个Session实例，
     * 如果该Session未关闭，后续多次获取的是同一个Session实例；事务提交或者回滚时会自动关闭Sesison,无需人工关闭。
     * @return
     */
    public Session currentSession(){
        return sessionFactory.getCurrentSession();
    }
    public List<User> findUserByUserName(String userName) {
        return  currentSession().createCriteria(User.class)
                .add(Restrictions.eq("username",userName)).list();
    }
    public User findByUserName(String userName) {
        return  (User)currentSession().createCriteria(User.class)
                .add(Restrictions.eq("username",userName)).list().get(0);
    }
    public User findOne1(long id) {
        return (User)currentSession().get(User.class,id);
    }
    public User findOne2(long id) {
        return (User)currentSession().get(User.class,id);
    }
    public User save(User user){
        Serializable id = currentSession().save(user);
        return new User((Integer)id,user.getUserName(),user.getPassword());
    }
    public List<User> findAll(){
        return currentSession().createCriteria(User.class).list();
    }
    public long count(){
        return findAll().size();
    }

}
