package com.example.greenshop.mapper;

import com.example.greenshop.dto.ratingsreviewDto.CreateRatingsreviewRequestDto;
import com.example.greenshop.dto.ratingsreviewDto.RatingsreviewDto;
import com.example.greenshop.dto.ratingsreviewDto.UpdateRatingsreviewRequestDto;
import com.example.greenshop.entity.Ratingsreview;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingsreviewMapper {

    Ratingsreview map(CreateRatingsreviewRequestDto dto);
    RatingsreviewDto mapToDto(RatingsreviewMapper entity);
    Ratingsreview updateDto(UpdateRatingsreviewRequestDto entity);
}
