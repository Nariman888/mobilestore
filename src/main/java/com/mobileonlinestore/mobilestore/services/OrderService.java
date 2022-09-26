package com.mobileonlinestore.mobilestore.services;

import com.mobileonlinestore.mobilestore.entities.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersByUser(Long id);
    Order addOrder(Order order);
}
