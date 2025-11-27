package com.example.UMC9th.domain.review.controller;
import com.example.UMC9th.domain.review.dto.ReviewReqDTO;
import com.example.UMC9th.domain.review.dto.ReviewResDTO;
import com.example.UMC9th.domain.review.dto.ReviewResponseDTO;
import com.example.UMC9th.domain.review.service.ReviewCommandService;
import com.example.UMC9th.domain.review.service.query.ReviewQueryService;
import com.example.UMC9th.global.apiPayload.ApiResponse;
import com.example.UMC9th.domain.review.exception.code.ReviewSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping
    public ApiResponse<ReviewResDTO.CreateDTO> createReview(
            @RequestBody ReviewReqDTO.CreateDTO request
    ) {
        ReviewResDTO.CreateDTO result = reviewCommandService.createReview(request);
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, result);
    }

    // 가게의 리뷰 목록 조회
    //swagger 관련 8,9주차 살펴보기
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 해태 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam Integer page
    ){

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }


    // 내가 작성한 리뷰 목록 조회
    @Operation(
            summary = "사용자의 리뷰 목록 조회 API By 해태 (개발 중)",
            description = "특정 사용자(본인)의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/users/{userId}/reviews")
    public ApiResponse<ReviewResponseDTO.MyReviewPreViewListDTO> getMyReviews(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(
                code,
                reviewQueryService.findMyReviews(userId, page)
        );
    }

}
