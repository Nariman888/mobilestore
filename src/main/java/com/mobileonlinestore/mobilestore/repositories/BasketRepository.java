package com.mobileonlinestore.mobilestore.repositories;

import com.mobileonlinestore.mobilestore.entities.Basket;
import com.mobileonlinestore.mobilestore.entities.Phone;
import com.mobileonlinestore.mobilestore.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BasketRepository extends JpaRepository<Basket,Long> {
    List<Basket> findAllByUserId(Long id);
    Basket findByUserAndPhone(Users user, Phone phone);
    void deleteByPhoneId(Long id);
    void deleteBasketsByUserId(Long id);
}
