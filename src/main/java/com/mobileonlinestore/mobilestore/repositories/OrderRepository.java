package com.mobileonlinestore.mobilestore.repositories;

import com.mobileonlinestore.mobilestore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUserId(Long id);
}
