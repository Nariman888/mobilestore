package com.mobileonlinestore.mobilestore.services.imp;

import com.mobileonlinestore.mobilestore.entities.Basket;
import com.mobileonlinestore.mobilestore.entities.Favorites;
import com.mobileonlinestore.mobilestore.repositories.FavoriteRepository;
import com.mobileonlinestore.mobilestore.services.FavoritesService;
import com.mobileonlinestore.mobilestore.services.PhoneService;
import com.mobileonlinestore.mobilestore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class FavoriteServiceImp implements FavoritesService {

    private final FavoriteRepository favoriteRepository;
    private final PhoneService phoneService;
    private final UserService userService;

    @Override
    public Favorites addtoFavorite(Long phoneId) {
        Favorites favorite = new Favorites();
        favorite.setPhone(phoneService.getPhoneDetails(phoneId));
        favorite.setUser(userService.getCurrentUser());
        List<Favorites> favoritesList = getFavoritesByUserId(userService.getCurrentUser().getId());
        boolean isExist =false;
        if (favoritesList.size()!=0) {
            for (Favorites it : favoritesList) {
                if (it.getPhone().getId() == phoneId) {
                    isExist =true;
                }
            }
            if (isExist==false) favoriteRepository.save(favorite);
            else return null;
        }else return favoriteRepository.save(favorite);

        return null;
    }

    @Override
    public List<Favorites> getFavoritesByUserId(Long id) {
        return favoriteRepository.findAllByUserId(id);
    }

    @Override
    public void deleteFromFavorites(Long id) {
        favoriteRepository.deleteById(id);
    }
}
