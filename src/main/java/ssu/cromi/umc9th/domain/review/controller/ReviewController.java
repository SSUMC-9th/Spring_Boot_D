package ssu.cromi.umc9th.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssu.cromi.umc9th.domain.review.dto.MyReviewResponseDto;
import ssu.cromi.umc9th.domain.review.dto.ReviewFilterDto;
import ssu.cromi.umc9th.domain.review.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 내가 작성한 리뷰 조회 (동적 필터링)
     *
     * 사용 예시:
     * 1. 모든 리뷰 조회: GET /api/reviews/my?userId=1
     * 2. 특정 가게 리뷰만: GET /api/reviews/my?userId=1&storeId=5
     * 3. 5점 리뷰만: GET /api/reviews/my?userId=1&minScore=5&maxScore=5
     * 4. 4점대 리뷰만: GET /api/reviews/my?userId=1&minScore=4&maxScore=4.99
     * 5. 가게+별점 조합: GET /api/reviews/my?userId=1&storeId=5&minScore=4&maxScore=5
     */
    @GetMapping("/my")
    public ResponseEntity<Page<MyReviewResponseDto>> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Float minScore,
            @RequestParam(required = false) Float maxScore,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        ReviewFilterDto filter = ReviewFilterDto.builder()
                .storeId(storeId)
                .minScore(minScore)
                .maxScore(maxScore)
                .build();

        Page<MyReviewResponseDto> reviews = reviewService.getMyReviews(userId, filter, pageable);
        return ResponseEntity.ok(reviews);
    }

    /**
     * 5점 리뷰만 조회 (편의 메서드)
     */
    @GetMapping("/my/five-star")
    public ResponseEntity<Page<MyReviewResponseDto>> getMyFiveStarReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) Long storeId,
            @PageableDefault(size = 10) Pageable pageable) {

        ReviewFilterDto filter = ReviewFilterDto.builder()
                .storeId(storeId)
                .minScore(5.0f)
                .maxScore(5.0f)
                .build();

        Page<MyReviewResponseDto> reviews = reviewService.getMyReviews(userId, filter, pageable);
        return ResponseEntity.ok(reviews);
    }
}