package com.example.greenshop.dto.ratingsreviewDto;

import com.example.greenshop.dto.productDto.ProductDto;
import com.example.greenshop.dto.userDto.UserDto;
import com.example.greenshop.entity.Product;
import com.example.greenshop.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class RatingsreviewDto {

    private int id;
    private UserDto userDto;
    private ProductDto productDto;
    private double rating;
    private String review;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dateTime;

}
