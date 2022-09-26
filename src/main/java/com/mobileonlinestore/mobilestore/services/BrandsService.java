package com.mobileonlinestore.mobilestore.services;

import com.mobileonlinestore.mobilestore.entities.Brands;

import java.util.List;

public interface BrandsService {
    Brands addBrand(Brands brand);
    List<Brands> getAllBrands();
    Brands getBrand(Long id);
    void deleteBrand(Long id);
    Brands updateBrand(Brands brand);
}
