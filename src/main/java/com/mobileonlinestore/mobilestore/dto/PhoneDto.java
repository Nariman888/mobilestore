package com.mobileonlinestore.mobilestore.dto;

import com.mobileonlinestore.mobilestore.entities.Brands;
import com.mobileonlinestore.mobilestore.entities.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;

@Getter
@Setter
public class PhoneDto {
    private Long id;
    private String photo;
    private String model;
    private String display;
    private String memory;
    private String camera;
    private String processor;
    private String interfaces;
    private String weight;
    private int price;
    private int amount;
    private Category category;
    private BrandsDto brand;
}
