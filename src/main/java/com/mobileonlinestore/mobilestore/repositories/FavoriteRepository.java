package com.mobileonlinestore.mobilestore.repositories;

import com.mobileonlinestore.mobilestore.entities.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FavoriteRepository extends JpaRepository<Favorites,Long> {
    List<Favorites> findAllByUserId(Long id);
}
