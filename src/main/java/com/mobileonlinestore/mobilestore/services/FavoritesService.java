package com.mobileonlinestore.mobilestore.services;

import com.mobileonlinestore.mobilestore.entities.Favorites;

import java.util.List;

public interface FavoritesService {
    Favorites addtoFavorite(Long id);
    List<Favorites> getFavoritesByUserId(Long id);
    void deleteFromFavorites(Long id);
}
