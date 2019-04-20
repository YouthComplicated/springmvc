package com.lanmo.controller;

import com.lanmo.entity.Teacher;
import com.lanmo.myexception.MyError;
import com.lanmo.myexception.TeacherNotFoundException;
import com.lanmo.service.impl.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class RestFulController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping(value="/list")
    public List<Teacher> findTeachers(@RequestParam(value = "max") long max){
        return null;
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MyError TeacherNotFound(TeacherNotFoundException e){
        long id = e.getTeacherId();
        return new MyError(4,"Teacher are"+id+" not found!");
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Teacher> testResponseEntity(@RequestBody Teacher teacher){
        Teacher t = new Teacher();
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = URI.create(
                "http://localhost:8080/test/demos"
        );
        headers.setLocation(locationUri);
        return new ResponseEntity<Teacher>(t,headers,HttpStatus.CREATED);
    }

    public void testHttpClient(){
    }

}
