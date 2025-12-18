package com.example.umc9th2.domain.review.controller;

import com.example.umc9th2.domain.review.dto.ReviewRequestDto;
import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import com.example.umc9th2.domain.review.service.command.ReviewCommandService;
import com.example.umc9th2.domain.review.service.query.ReviewQueryService;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th2.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th2.global.annotation.ValidPage;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    //리뷰 작성
    @PostMapping("/{storeId}")
    public ApiResponse<ReviewResponseDto> createReview(
            @PathVariable Long storeId,
            @Validated @RequestBody ReviewRequestDto.CreateDTO dto
    ) {
        ReviewResponseDto result = reviewCommandService.createReview(storeId, dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.REVIEW_CREATE_SUCCESS, result);
    }

    //리뷰 검색
    @GetMapping("/search")
    public ApiResponse<List<ReviewResponseDto>> getFilteredReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Float rating
    ) {
        List<ReviewResponseDto> result =
                reviewQueryService.getFilteredReviews(userId, storeName, rating);

        return ApiResponse.onSuccess(GeneralSuccessCode.REVIEW_READ_SUCCESS, result);
    }

    //특정 가게 리뷰 목록 조회
    @GetMapping
    public ApiResponse<ReviewResponseDto.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @ValidPage @RequestParam Integer page
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                reviewQueryService.findReview(storeName, page)
        );
    }

    //내가 작성한 리뷰 목록 조회
    @GetMapping("/my")
    public ApiResponse<ReviewResponseDto.ReviewPreViewListDTO> getMyReviews(
            @RequestParam Long userId,//어떤 사용자의 리뷰인지
            @ValidPage @RequestParam Integer page//몇 번째 페이지인지(1페이지부터 시작)
    ) {
        //서비스 호출->페이징된 리뷰 목록 반환
        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                reviewQueryService.getMyReviews(userId, page)
        );
    }
}
