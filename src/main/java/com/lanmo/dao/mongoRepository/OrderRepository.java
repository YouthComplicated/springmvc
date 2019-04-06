package com.lanmo.dao.mongoRepository;

import com.lanmo.dao.OrderOperations;
import com.lanmo.entity.mongo.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String>, OrderOperations {
    List<Order> findByCustomer(String c);
    List<Order> findByCustomerLike(String c);
    List<Order> findByCustomerAndType(String c, String t);
    List<Order> findByCustomerLikeAndType(String c, String t);

    @Query("{'customer':'tom','type': ?0}")
    List<Order> findChuckOrdersOrders(String t);
}
