package com.mobileonlinestore.mobilestore.services.imp;

import com.mobileonlinestore.mobilestore.entities.Brands;
import com.mobileonlinestore.mobilestore.entities.Category;
import com.mobileonlinestore.mobilestore.entities.Phone;
import com.mobileonlinestore.mobilestore.repositories.PhoneRepository;
import com.mobileonlinestore.mobilestore.services.BrandsService;
import com.mobileonlinestore.mobilestore.services.CategoryService;
import com.mobileonlinestore.mobilestore.services.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PhoneServiceImp implements PhoneService {

    private final PhoneRepository phoneRepository;
    private final CategoryService categoryService;
    private final BrandsService brandsService;

    @Override
    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    @Override
    public Phone getPhoneDetails(Long id) {
        return phoneRepository.findById(id).orElseThrow();
    }

    @Override
    public Phone addPhone(Phone phone) {
            return phoneRepository.save(phone);
    }

    @Override
    public Phone updatePhone(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public void delete(Long id) {
        phoneRepository.deleteById(id);
    }

    @Override
    public List<Phone> getPhonesByCategoryId(Long id) {
        return phoneRepository.findAllByCategoryId(id);
    }

    @Override
    public List<Phone> getPhonesByModel(String model) {
        return phoneRepository.searchByModelContainingIgnoreCaseOrderByModelAsc(model);
    }
    public List<Phone> getAllByOrderById(){
        return phoneRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Phone> getAllByPriceRange(int from, int till) {
        return phoneRepository.findAllByPriceBetweenOrderByPriceDesc(from, till);
    }

    @Override
    public List<Phone> getAllByPriceMinToMax() {
        return phoneRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<Phone> getAllByPriceMaxToMin() {
        return phoneRepository.findAllByOrderByPriceDesc();
    }

    @Override
    public List<Phone> getPhonesByBrand(Long brandId) {
        return phoneRepository.findAllByBrandId(brandId);
    }
}
