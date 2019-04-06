package com.lanmo.dao.impl;

import com.lanmo.dao.OrderOperations;
import com.lanmo.entity.mongo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderOperations {

    @Autowired
    private MongoOperations mongo;

    @Override
    public List<Order> findOrdersByType(String t) {
        String type = t.equals("web")? "aa":t;
        Criteria where = Criteria.where(type).is(t);
        Query query = Query.query(where);
        return mongo.find(query, Order.class);
    }
}
