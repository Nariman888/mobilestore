package com.mobileonlinestore.mobilestore.repositories;

import com.mobileonlinestore.mobilestore.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RatingRepository extends JpaRepository<Rating,Long> {
}
