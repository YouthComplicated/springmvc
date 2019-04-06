package com.lanmo.dao;

import com.lanmo.entity.mongo.Order;

import java.util.List;

public interface OrderOperations {
    List<Order> findOrdersByType(String t);
}
