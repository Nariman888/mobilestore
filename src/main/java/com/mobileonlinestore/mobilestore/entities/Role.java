package com.mobileonlinestore.mobilestore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {
    @Column(name = "role")
    private String role;
    @Override
    public String getAuthority() {
        return this.role;
    }
 }
