package com.mobileonlinestore.mobilestore.services;

import com.mobileonlinestore.mobilestore.entities.Basket;
import com.mobileonlinestore.mobilestore.entities.Favorites;
import com.mobileonlinestore.mobilestore.entities.Phone;
import com.mobileonlinestore.mobilestore.entities.Users;

import java.util.List;

public interface BasketService {
    Basket addToBasket(Long phoneId);
    List<Basket> getBasketByUserId(Long id);
    void deleteFromBasket(Long id);
    List<Favorites> getAddedFavorites(Users user, List<Favorites> favorites);
    void deleteByPhoneId(Long phoneId);
    void deleteBasketsbyUser(Long id);
}
