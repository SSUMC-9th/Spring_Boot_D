package ssu.cromi.umc9th.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import ssu.cromi.umc9th.domain.review.entity.UserReview;
import ssu.cromi.umc9th.domain.review.exception.code.ReviewSuccessCode;
import ssu.cromi.umc9th.domain.review.service.ReviewService;
import ssu.cromi.umc9th.domain.review.service.query.ReviewQueryService;
import ssu.cromi.umc9th.global.apiPayload.ApiResponse;
import ssu.cromi.umc9th.global.apiPayload.code.GeneralSuccessCode;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController{

    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;

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

    @Operation(
            summary = "가게의 리뷰 목록 조회 API By Crom(개발중)",
            description = "특정 가게의 리뷰를 모두 조회. 페이지네이션으로 제공"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam Integer page
    ){

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName,page));
    }

    public List<UserReview> searchReview(
            @RequestParam String filter,
            @RequestParam String type
    )throws Exception{
        List<UserReview> result = reviewQueryService.searchReview(filter, type);
        return result;
    }
}