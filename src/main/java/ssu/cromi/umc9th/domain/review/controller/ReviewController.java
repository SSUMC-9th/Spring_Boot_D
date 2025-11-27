package ssu.cromi.umc9th.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ssu.cromi.umc9th.domain.review.dto.MyReviewResponseDto;
import ssu.cromi.umc9th.domain.review.dto.ReviewFilterDto;
import ssu.cromi.umc9th.domain.review.dto.ReviewReqDTO;
import ssu.cromi.umc9th.domain.review.dto.ReviewResDTO;
import ssu.cromi.umc9th.domain.review.exception.code.ReviewSuccessCode;
import ssu.cromi.umc9th.domain.review.service.ReviewService;
import ssu.cromi.umc9th.global.apiPayload.ApiResponse;
import ssu.cromi.umc9th.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 가게에 리뷰 추가
     */
    @PostMapping
    public ApiResponse<ReviewResDTO.CreateDTO> createReview(@RequestBody ReviewReqDTO.CreateDTO request) {
        ReviewResDTO.CreateDTO response = reviewService.createReview(request);

        return ApiResponse.onSuccess(ReviewSuccessCode.SUCCESS_REVIEWED, response);
    }

    /**
     * 내가 작성한 리뷰 조회
     */
    @GetMapping("/my")
    public ApiResponse<Page<MyReviewResponseDto>> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer score,
            @RequestParam(required = false) Float minScore,
            @RequestParam(required = false) Float maxScore,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        ReviewFilterDto filter;
        // score 파라미터가 있으면 자동 범위 설정
        if (score != null) {
            filter = ReviewFilterDto.of(storeId, score);
        }
        // 없으면 기존대로
        else {
            filter = ReviewFilterDto.builder()
                    .storeId(storeId)
                    .minScore(minScore)
                    .maxScore(maxScore)
                    .build();
        }

        Page<MyReviewResponseDto> reviews = reviewService.getMyReviews(userId, filter, pageable);
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviews);
    }

    /**
     * 특정 별점 리뷰만 조회
     */
    @GetMapping("/my/star")
    public ApiResponse<Page<MyReviewResponseDto>> getMyReviewsByStar(
            @RequestParam Long userId,
            @RequestParam Integer score,
            @RequestParam(required = false) Long storeId,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        ReviewFilterDto filter = ReviewFilterDto.of(storeId, score);
        Page<MyReviewResponseDto> reviews = reviewService.getMyReviews(userId, filter, pageable);
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviews);
    }
}