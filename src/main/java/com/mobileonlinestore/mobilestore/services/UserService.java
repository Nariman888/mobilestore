package com.mobileonlinestore.mobilestore.services;

import com.mobileonlinestore.mobilestore.dto.UserDto;
import com.mobileonlinestore.mobilestore.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto getUser(String email);
    Users addUser(Users user,String rePassword);
    Users getCurrentUser();
    boolean updateUserPassword(String oldPassword,String newPassword,String reNewPassword);
    Users updateUser(Users user);
}
