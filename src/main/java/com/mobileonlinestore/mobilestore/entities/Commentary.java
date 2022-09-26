package com.mobileonlinestore.mobilestore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Commentary extends BaseEntity {
    @Column(name = "comment")
    private String commentary;
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
