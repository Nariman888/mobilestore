package com.mobileonlinestore.mobilestore.services.imp;

import com.mobileonlinestore.mobilestore.entities.Brands;
import com.mobileonlinestore.mobilestore.repositories.BrandsRepository;
import com.mobileonlinestore.mobilestore.services.BrandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandsServiceImp implements BrandsService {

    @Autowired
    BrandsRepository brandsRepository;
    @Override
    public Brands addBrand(Brands brand) {
        return brandsRepository.save(brand);
    }

    @Override
    public List<Brands> getAllBrands() {
        return brandsRepository.findAll();
    }

    @Override
    public Brands getBrand(Long id) {
        return brandsRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteBrand(Long id) {
        brandsRepository.deleteById(id);
    }

    @Override
    public Brands updateBrand(Brands brand) {
        Brands newBrand = getBrand(brand.getId());
        newBrand.setName(brand.getName());
        newBrand.setCountry(brand.getCountry());
        return brandsRepository.save(newBrand);
    }
}
