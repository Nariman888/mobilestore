package com.mobileonlinestore.mobilestore.services.imp;

import com.mobileonlinestore.mobilestore.entities.Order;
import com.mobileonlinestore.mobilestore.repositories.OrderRepository;
import com.mobileonlinestore.mobilestore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getOrdersByUser(Long id) {
        return orderRepository.findAllByUserId(id);
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }
}
