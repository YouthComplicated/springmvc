package com.lanmo.controller;

import com.lanmo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/test/mongo")
    public Map<String, Object> TestMongo(){
        orderService.testMongo();
        return null;
    }


}
