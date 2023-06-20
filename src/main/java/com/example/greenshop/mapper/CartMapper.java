package com.example.greenshop.mapper;

import com.example.greenshop.dto.cartDto.CartDto;
import com.example.greenshop.dto.cartDto.CreateCartRequestDto;
import com.example.greenshop.dto.cartDto.UpdateCartRequestDto;
import com.example.greenshop.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    Cart map(CreateCartRequestDto dto);
    CartDto mapToDto(Cart entity);
    Cart updateDto(UpdateCartRequestDto entity);
}
