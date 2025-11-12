package com.example.umc9th2.domain.review.controller;

import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import com.example.umc9th2.domain.review.service.ReviewQueryService;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/search")
    public ApiResponse<List<ReviewResponseDto>> getFilteredReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Float rating
    ) {
        List<ReviewResponseDto> result = reviewQueryService.getFilteredReviews(userId, storeName, rating);
        return ApiResponse.onSuccess(GeneralSuccessCode.REVIEW_READ_SUCCESS, result);
    }
}
