package com.lanmo.service;

import com.lanmo.dao.TeacherDao;
import com.lanmo.dao.impl.JpaTeacherRepository;
import com.lanmo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherDao jpaTeacherRepository;

    public void TestJPA(){
        jpaTeacherRepository.insert(new Teacher("111",(char)1,123));
        jpaTeacherRepository.save(new Teacher(2,"李老师",(char)1,30));
        System.out.println(jpaTeacherRepository.findOne1(1));
    }
}
