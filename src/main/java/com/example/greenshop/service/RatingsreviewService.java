package com.example.greenshop.service;


import com.example.greenshop.dto.productDto.ProductDto;
import com.example.greenshop.dto.ratingsreviewDto.CreateRatingsreviewRequestDto;
import com.example.greenshop.dto.ratingsreviewDto.RatingsreviewDto;
import com.example.greenshop.dto.ratingsreviewDto.UpdateRatingsreviewRequestDto;
import com.example.greenshop.entity.Product;
import com.example.greenshop.entity.Ratingsreview;
import com.example.greenshop.security.CurrentUser;

import java.util.List;

public interface RatingsreviewService {

    List<RatingsreviewDto> getAllByProductId(int productId);
    List<RatingsreviewDto> getAll();

    void createReviewAndRating(CreateRatingsreviewRequestDto createRatingsreviewRequestDto, CurrentUser currentUser);

    Ratingsreview getRatingsreviewByUserId(CurrentUser currentUser);

    void updateRatingsreview(UpdateRatingsreviewRequestDto ratingsreview, CurrentUser currentUser);

    void deleteRatingsreview(int id);
    public double calculateProductRating(List<RatingsreviewDto> ratingsreviews);

    public List<ProductDto> allProductsRating();
}
