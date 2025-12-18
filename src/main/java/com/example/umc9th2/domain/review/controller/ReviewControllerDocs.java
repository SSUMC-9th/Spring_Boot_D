package com.example.umc9th2.domain.review.controller;

import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.annotation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Review", description = "리뷰 조회 및 검색 API")
public interface ReviewControllerDocs {

    //특정 가게 리뷰 목록 조회
    @Operation(
            summary = "가게 리뷰 목록 조회 API",
            description = "특정 가게(storeName)의 리뷰를 페이지네이션 기반으로 조회합니다. (10개씩)"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "조회 실패")
    })
    ApiResponse<ReviewResponseDto.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @ValidPage @RequestParam Integer page
    );

    //내가 작성한 리뷰 목록 조회
    @Operation(
            summary = "내가 작성한 리뷰 목록 조회",
            description = "특정 사용자가 작성한 리뷰 목록을 페이징(10개씩)으로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    ApiResponse<ReviewResponseDto.ReviewPreViewListDTO> getMyReviews(
            @RequestParam Long userId,
            @ValidPage @RequestParam Integer page
    );
}
