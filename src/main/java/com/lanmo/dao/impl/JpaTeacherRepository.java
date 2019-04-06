package com.lanmo.dao.impl;

import com.lanmo.dao.TeacherDao;
import com.lanmo.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.io.Serializable;
import java.util.List;

/**
 * 使用entityManager 进行操作(jpa)
 */
@Repository
@Transactional( propagation = Propagation.REQUIRED)
public class JpaTeacherRepository implements TeacherDao {

//    private SessionFactory sessionFactory;
//    @Inject
//    public JpaTeacherRepository(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    /**
     * 每次重新创建entityManager会有线程安全性问题
     */
//    @PersistenceUnit
//    private EntityManagerFactory entityManagerFactory;

    /**
     * 直接注入entityManager的代理，与当前事务相关联
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Teacher> findUserByUserName(String userName) {
        return null;
    }

    @Override
    public Teacher findByUserName(String userName) {
        return null;
    }

    @Override
    public Teacher findOne1(Integer id) {
//        return entityManagerFactory.createEntityManager().find(Teacher.class,id);
        return entityManager.find(Teacher.class,id);
    }

    @Override
    public Teacher findOne2(Integer id) {
//        return entityManagerFactory.createEntityManager().find(Teacher.class,id);
        return entityManager.find(Teacher.class,id);
    }
    @Override
    public Teacher save(Teacher teacher) {
//        entityManagerFactory.createEntityManager().merge(teacher);
        try {
            entityManager.merge(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        return  teacher;
    }
    @Override
    public void insert(Teacher teacher) {
//        entityManagerFactory.createEntityManager().persist(teacher);
        try {
            entityManager.persist(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<Teacher> findAll() {
        return null;
    }

/**
     *（1）openSession每次打开都是新的Session，所以多次获取的Session实例是不同的，并且需要人为的调用close方法进行Session关闭。
     *（2）getCurrentSession是从当前上下文中获取Session并且会绑定到当前线程，第一次调用时会创建一个Session实例，
     * 如果该Session未关闭，后续多次获取的是同一个Session实例；事务提交或者回滚时会自动关闭Sesison,无需人工关闭。
     * @return
     */
//    public Session currentSession(){
//        return sessionFactory.getCurrentSession();
//    }
//    public Teacher save(Teacher teacher){
//        Serializable id = currentSession().save(teacher);
//        return new Teacher((Integer)id,teacher.getUserName(),teacher.getSex(),teacher.getAge());
//    }
//    public Teacher findOne(Integer id ){
//        return (Teacher)currentSession().get(Teacher.class,id);
//    }
//    public Teacher findByUsername(String userName){
//        return  (Teacher)currentSession().createCriteria(Teacher.class)
//                .add(Restrictions.eq("username",userName)).list().get(0);
//    }
//    public List<Teacher> findAll(){
//        return currentSession().createCriteria(Teacher.class).list();
//    }
//    public long count(){
//        return findAll().size();
//    }


}
