package com.example.umc9th2.domain.review.controller;

import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import com.example.umc9th2.domain.review.service.ReviewQueryService;
import com.example.umc9th2.global.code.SuccessStatus;
import com.example.umc9th2.global.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/search")
    public BaseResponse<List<ReviewResponseDto>> getFilteredReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Float rating
    ) {
        List<ReviewResponseDto> result = reviewQueryService.getFilteredReviews(userId, storeName, rating);
        return BaseResponse.onSuccess(SuccessStatus.REVIEW_READ_SUCCESS, result);
    }
}

