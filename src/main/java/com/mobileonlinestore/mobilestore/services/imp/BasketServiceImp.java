package com.mobileonlinestore.mobilestore.services.imp;

import com.mobileonlinestore.mobilestore.entities.Basket;
import com.mobileonlinestore.mobilestore.entities.Favorites;
import com.mobileonlinestore.mobilestore.entities.Phone;
import com.mobileonlinestore.mobilestore.entities.Users;
import com.mobileonlinestore.mobilestore.repositories.BasketRepository;
import com.mobileonlinestore.mobilestore.repositories.PhoneRepository;
import com.mobileonlinestore.mobilestore.services.BasketService;
import com.mobileonlinestore.mobilestore.services.PhoneService;
import com.mobileonlinestore.mobilestore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BasketServiceImp implements BasketService {

    private final BasketRepository basketRepository;
    private final UserService userService;
    private final PhoneService phoneService;

    @Override
    public Basket addToBasket(Long phoneId) {
        Basket basket = new Basket();
        basket.setPhone(phoneService.getPhoneDetails(phoneId));
        basket.setUser(userService.getCurrentUser());

        List<Basket> itemList= getBasketByUserId(userService.getCurrentUser().getId());
        boolean isExist = false;
        if (itemList.size()!=0) {
            for (Basket it : itemList) {
                if (it.getPhone().getId() == phoneId) {
                    isExist =true;
                }
            }
            if (isExist==false) basketRepository.save(basket);
            else return null;
        }else return basketRepository.save(basket);
        return null;
    }

    @Override
    public List<Basket> getBasketByUserId(Long userid) {
        return basketRepository.findAllByUserId(userid);
    }

    @Override
    public void deleteFromBasket(Long id) {
        basketRepository.deleteById(id);
    }

    @Override
    public List<Favorites> getAddedFavorites(Users user, List<Favorites> favorites) {
        List<Favorites> addedFavorites = new ArrayList<>();
        for (Favorites fav : favorites){
            if (basketRepository.findByUserAndPhone(user, fav.getPhone())!=null){
                addedFavorites.add(fav);
            }

        }
        return addedFavorites;
    }

    @Override
    public void deleteByPhoneId(Long phoneId) {
        basketRepository.deleteByPhoneId(phoneId);
    }

    @Override
    public void deleteBasketsbyUser(Long id) {
        basketRepository.deleteBasketsByUserId(id);
    }
}
