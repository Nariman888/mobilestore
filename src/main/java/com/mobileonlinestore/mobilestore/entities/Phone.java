package com.mobileonlinestore.mobilestore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phone extends BaseEntity {
    private String photo;
    private String model;
    private String memory;
    private String camera;
    private String processor;
    private String interfaces;
    private int price;
    private int amount;
    @ManyToOne
    Category category;
    @ManyToOne
    Brands brand;

    @PrePersist
    private void prepersist(){
        photo="default";
    }

}
