package com.mobileonlinestore.mobilestore.repositories;

import com.mobileonlinestore.mobilestore.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PhoneRepository extends JpaRepository<Phone,Long> {
    List<Phone> findAllByCategoryId(Long Id);
    List<Phone> searchByModelContainingIgnoreCaseOrderByModelAsc(String model);
    List<Phone> findAllByOrderByIdAsc();
    List<Phone> findAllByPriceBetweenOrderByPriceDesc(int i1,int i2);
    List<Phone> findAllByBrandId(Long brand);
    List<Phone> findAllByOrderByPriceDesc();
    List<Phone> findAllByOrderByPriceAsc();
}
