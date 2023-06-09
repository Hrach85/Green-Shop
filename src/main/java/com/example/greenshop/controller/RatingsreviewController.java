package com.example.greenshop.controller;

import com.example.greenshop.dto.ratingsreviewDto.CreateRatingsreviewRequestDto;
import com.example.greenshop.dto.ratingsreviewDto.UpdateRatingsreviewRequestDto;
import com.example.greenshop.entity.Ratingsreview;
import com.example.greenshop.security.CurrentUser;
import com.example.greenshop.service.RatingsreviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ratingsreviews")
@RequiredArgsConstructor
public class RatingsreviewController {

    private final RatingsreviewService ratingsreviewService;

    @PostMapping("/create")
    public String createRatingsreview(@ModelAttribute("ratingsreview") CreateRatingsreviewRequestDto createRatingsreviewRequestDto,
                                      @AuthenticationPrincipal CurrentUser currentUser) {
        ratingsreviewService.createReviewAndRating(createRatingsreviewRequestDto, currentUser);
        return "redirect:/products";
    }

    @PostMapping("/update/{id}")
    public String updateRatingsreview(@PathVariable int id,
                                      @ModelAttribute("ratingsreview") UpdateRatingsreviewRequestDto updatedRatingsreview,
                                      @AuthenticationPrincipal CurrentUser currentUser) {
        ratingsreviewService.updateRatingsreview(updatedRatingsreview, currentUser);
        return "redirect:/products" ;
    }

    @PostMapping("/delete/{id}")
    public String deleteRatingsreview(@PathVariable int id) {
        ratingsreviewService.deleteRatingsreview(id);
        return "redirect:/products";
    }
}
