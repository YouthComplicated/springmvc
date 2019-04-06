package com.lanmo.service;

import com.lanmo.dao.TeacherDao;
import com.lanmo.dao.JpaRepository.TeacherSpringDataDao;
import com.lanmo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired(required = true)
    private TeacherSpringDataDao teacherSpringDataDao;


    public void TestJPA(){
//        teacherDao.insert(new Teacher("111",(char)1,123));
//        teacherDao.save(new Teacher(2,"李老师",(char)1,30));
        //System.out.println(teacherDao.findOne1(1));

        Teacher teacher = teacherDao.findOne1(1);
        teacher.setUserName("aaa");
        teacherDao.save(teacher);
    }

    public void TestSpringData(){
        Teacher  teacher = teacherSpringDataDao.getOne(1);
        System.out.println(teacher);
        System.out.println(teacherSpringDataDao.findAllTeacher());
        teacher.setUserName("aaa");
        teacherSpringDataDao.save(teacher);
    }
}
