package com.mobileonlinestore.mobilestore.repositories;

import com.mobileonlinestore.mobilestore.entities.Category;
import com.mobileonlinestore.mobilestore.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
