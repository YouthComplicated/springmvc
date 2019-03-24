package com.lanmo.controller;

import com.lanmo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping("/hello")
    public String Hello(){
        System.out.println(studentService.saySomething());
        return "success";
    }

    //WEB-INF/views/thello.jsp
    @RequestMapping("/say")
    public String Hello1(){
//        System.out.println(studentService.saySomething());
        return "thello";
    }


    //WEB-INF/views/thello.jsp
    @RequestMapping("/index")
    public String Hello11(){
//        System.out.println(studentService.saySomething());
        return "index";
    }

}
