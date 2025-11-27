package com.umc9th.peter.domain.review.controller;

import com.umc9th.peter.domain.review.dto.ReviewRequest;
import com.umc9th.peter.domain.review.dto.ReviewResponse;
import com.umc9th.peter.domain.review.exception.code.ReviewSuccessCode;
import com.umc9th.peter.domain.review.service.ReviewService;
import com.umc9th.peter.global.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController implements ReviewControllerDocs {

    private final ReviewService reviewService;

    @GetMapping
    public ApiResponse<ReviewResponse.ReviewListDto> getReviews(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer star,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        // TODO: 인증 기능 구현되면 토큰에서 memberId 파싱해서 사용 (API를 통한 멤버별 리뷰 조회 불가)
        ReviewResponse.ReviewListDto reviews = reviewService.getReviews(null, storeId, star, page, limit);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.OK,
                reviews
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse.ReviewDto>> writeReview(
            @RequestBody ReviewRequest.ReviewDto dto
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.CREATED;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onSuccess(
                        code,
                        // TODO: 인증 기능 구현되면 토큰에서 memberId 파싱해서 사용
                        reviewService.writeReview(1L, dto)
                ));
    }

}
