package com.mobileonlinestore.mobilestore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity{
    private Timestamp createdAt;
    @ManyToOne(fetch = FetchType.EAGER)
    Users user;
    @ManyToOne(fetch = FetchType.EAGER)
    Phone phone;
    @PrePersist
    protected void create(){
        createdAt=new Timestamp(System.currentTimeMillis());
    }
}
