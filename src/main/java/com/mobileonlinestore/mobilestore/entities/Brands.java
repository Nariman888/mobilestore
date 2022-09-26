package com.mobileonlinestore.mobilestore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brands extends BaseEntity {
    private String name;
    private String country;
}
