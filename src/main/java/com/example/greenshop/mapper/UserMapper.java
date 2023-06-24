package com.example.greenshop.mapper;

import com.example.greenshop.dto.userDto.CreateUserRequestDto;
import com.example.greenshop.dto.userDto.UserDto;
import com.example.greenshop.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(CreateUserRequestDto dto);
    UserDto mapToDto(User entity);
    User dtoToMap(UserDto entity);

}