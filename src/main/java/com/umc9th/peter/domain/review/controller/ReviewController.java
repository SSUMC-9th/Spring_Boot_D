package com.umc9th.peter.domain.review.controller;

import com.umc9th.peter.domain.review.dto.ReviewResponse;
import com.umc9th.peter.domain.review.exception.code.ReviewSuccessCode;
import com.umc9th.peter.domain.review.service.ReviewService;
import com.umc9th.peter.global.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ApiResponse<List<ReviewResponse.ReviewDto>> getReviews(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer star
    ) {
        // TODO: 인증 기능 구현되면 토큰에서 memberId 파싱해서 사용 (API를 통한 멤버별 리뷰 조회 불가)
        List<ReviewResponse.ReviewDto> reviews = reviewService.getReviews(null, storeId, star);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.OK,
                reviews
        );
    }

}
