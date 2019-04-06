package com.lanmo.service;

import com.lanmo.dao.mongoRepository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void testMongo(){
        System.out.println(orderRepository.findAll());
        System.out.println(orderRepository.findOrdersByType("web"));
    }
}
