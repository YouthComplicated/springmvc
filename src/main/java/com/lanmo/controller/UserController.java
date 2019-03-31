package com.lanmo.controller;

import com.lanmo.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/get/user")
    public Map<String, Object> getUserByName(String name){
        Map<String, Object> result = new HashMap<>();
        result.put("user", userService.loadUserByUsername(name));
        return result;
    }

    @GetMapping("/test/hibernate")
    public Map<String, Object> testHibernate(){
        userService.testHibernate();
        return null;
    }
}
