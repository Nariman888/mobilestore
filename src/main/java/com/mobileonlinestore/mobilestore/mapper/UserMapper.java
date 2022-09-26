package com.mobileonlinestore.mobilestore.mapper;

import com.mobileonlinestore.mobilestore.dto.UserDto;
import com.mobileonlinestore.mobilestore.entities.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(Users user);
    Users toEntity(UserDto dto);
}
