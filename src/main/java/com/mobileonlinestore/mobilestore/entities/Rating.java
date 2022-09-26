package com.mobileonlinestore.mobilestore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating extends BaseEntity {
    private int rating;
    @ManyToOne(fetch = FetchType.EAGER)
    Users user;
    @ManyToOne(fetch = FetchType.EAGER)
    Phone phone;
}
