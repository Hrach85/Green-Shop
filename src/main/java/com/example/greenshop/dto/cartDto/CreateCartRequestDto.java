package com.example.greenshop.dto.cartDto;

import com.example.greenshop.dto.productDto.ProductDto;
import com.example.greenshop.dto.userDto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCartRequestDto {

    private int id;
    private UserDto userDto;
    private ProductDto productDto;
    private int quantity;

}
