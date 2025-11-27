package com.example.umc9th2.domain.review.controller;

import com.example.umc9th2.domain.review.dto.ReviewRequestDto;
import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import com.example.umc9th2.domain.review.service.command.ReviewCommandService;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewCommandController {

    private final ReviewCommandService reviewCommandService;

    // 리뷰 작성 API
    @PostMapping("/{storeId}")
    public ApiResponse<ReviewResponseDto> createReview(
            @PathVariable Long storeId,
            @Validated @RequestBody ReviewRequestDto.CreateDTO dto
    ) {
        ReviewResponseDto result = reviewCommandService.createReview(storeId, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.REVIEW_CREATE_SUCCESS, result);
    }
}

