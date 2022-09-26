package com.mobileonlinestore.mobilestore.repositories;

import com.mobileonlinestore.mobilestore.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByRole(String role);
}
