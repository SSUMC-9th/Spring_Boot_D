package com.umc9th.peter.domain.review.controller;

import com.umc9th.peter.domain.review.dto.ReviewRequest;
import com.umc9th.peter.domain.review.dto.ReviewResponse;
import com.umc9th.peter.global.annotation.PageNumber;
import com.umc9th.peter.global.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewControllerDocs {

    @Operation(
            summary = "내 리뷰 목록 조회",
            description = "리뷰를 가게id, 별점에 따라 필터링해서 조회합니다.\n\n" +
                    "**[주의]** 현재는 모든 사용자의 리뷰가 조회되며, 인증 기능 추가 후 자신의 리뷰만 조회되도록 수정 예정입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "조회 실패")
    })
    public ApiResponse<ReviewResponse.ReviewListDto> getReviews(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer star,
            @RequestParam(defaultValue = "1") @PageNumber Integer page,
            @RequestParam(defaultValue = "10") Integer limit
    );

    @Operation(
            summary = "리뷰 작성",
            description = "식당에 대한 리뷰를 작성합니다.\n\n" +
                    "**[주의]** 현재는 memberId가 1L로 기록되며, 인증 기능 추가 후 자신의 memberId로 기록되도록 수정 예정입니다."
    )
    public ResponseEntity<ApiResponse<ReviewResponse.ReviewDto>> writeReview(
            @RequestBody ReviewRequest.ReviewDto dto
    );

}
