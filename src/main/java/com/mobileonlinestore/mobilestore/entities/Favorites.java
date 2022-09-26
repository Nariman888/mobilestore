package com.mobileonlinestore.mobilestore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Favorites extends BaseEntity {
    @ManyToOne
    private Users user;
    @ManyToOne
    private Phone phone;
}
