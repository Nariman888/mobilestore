package com.mobileonlinestore.mobilestore.services.imp;

import com.mobileonlinestore.mobilestore.dto.UserDto;
import com.mobileonlinestore.mobilestore.entities.Users;
import com.mobileonlinestore.mobilestore.mapper.UserMapper;
import com.mobileonlinestore.mobilestore.repositories.UserRepository;
import com.mobileonlinestore.mobilestore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDto getUser(String email) {
        return userMapper.toDto(repository.findByEmail(email));
    }

    @Override
    public Users addUser(Users user, String rePassword) {
        if (rePassword.equals(user.getPassword())){
            Users foundUser = repository.findByEmail(user.getEmail());
            if (foundUser==null){
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return repository.save(user);
            }
        }
        return null;
    }

    @Override
    public Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (Users) authentication.getPrincipal();
        }
        return null;
    }

    @Override
    public boolean updateUserPassword(String oldPassword, String newPassword, String reNewPassword) {
        if (newPassword.equals(reNewPassword)){
            Users user = getCurrentUser();
            if (user!=null && !oldPassword.equals(newPassword) && passwordEncoder.matches(oldPassword,user.getPassword())){
                user.setPassword(passwordEncoder.encode(newPassword));
                repository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users foundUser= repository.findByEmail(username);
        if (foundUser!=null) {
            return foundUser;
        } else{
            throw new UsernameNotFoundException("User Not Found!");
        }
    }

    @Override
    public Users updateUser(Users user) {
        return repository.save(user);
    }
}
