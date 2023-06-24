package com.example.greenshop.mapper;

import com.example.greenshop.dto.orderDto.CreateOrderRequestDto;
import com.example.greenshop.dto.orderDto.OrderDto;
import com.example.greenshop.dto.orderDto.UpdateOrderRequestDto;
import com.example.greenshop.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order map(CreateOrderRequestDto dto);
    OrderDto mapToDto(Order entity);
    Order updateDto(UpdateOrderRequestDto entity);

}
