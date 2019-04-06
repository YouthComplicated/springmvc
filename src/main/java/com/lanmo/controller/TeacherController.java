package com.lanmo.controller;

import com.lanmo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/test/jpa")
    public void TestJPA(){
        teacherService.TestJPA();
    }

    @GetMapping("/test/springdata")
    public void TestSpringData() {
        teacherService.TestSpringData();
    }
}
