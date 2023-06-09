package com.example.greenshop.dto.orderDto;

import com.example.greenshop.dto.productDto.ProductDto;
import com.example.greenshop.dto.userDto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrderRequestDto {

    private int id;
    private UserDto userDto;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date orderDate;
    private ProductDto productDto;
    private int quantity;

}
