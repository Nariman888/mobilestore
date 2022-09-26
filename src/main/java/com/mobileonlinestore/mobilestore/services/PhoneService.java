package com.mobileonlinestore.mobilestore.services;

import com.mobileonlinestore.mobilestore.entities.Phone;

import java.util.List;

public interface PhoneService {
    List<Phone> getAllPhones();
    Phone getPhoneDetails(Long id);
    Phone addPhone(Phone phone);
    Phone updatePhone(Phone phone);
    void delete(Long id);
    List<Phone> getPhonesByCategoryId(Long id);
    List<Phone> getPhonesByModel(String model);
    List<Phone> getAllByOrderById();
    List<Phone> getAllByPriceRange(int from,int till);
    List<Phone> getAllByPriceMinToMax();
    List<Phone> getAllByPriceMaxToMin();
    List<Phone> getPhonesByBrand(Long brandId);
}
