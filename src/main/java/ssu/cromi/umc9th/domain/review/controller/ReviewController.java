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
     * 내가 작성한 리뷰 조회
     */
    @GetMapping("/my")
    public ResponseEntity<Page<MyReviewResponseDto>> getMyReviews(
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
        return ResponseEntity.ok(reviews);
    }

    /**
     * 특정 별점 리뷰만 조회
     */
    @GetMapping("/my/star")
    public ResponseEntity<Page<MyReviewResponseDto>> getMyReviewsByStar(
            @RequestParam Long userId,
            @RequestParam Integer score,
            @RequestParam(required = false) Long storeId,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        ReviewFilterDto filter = ReviewFilterDto.of(storeId, score);
        Page<MyReviewResponseDto> reviews = reviewService.getMyReviews(userId, filter, pageable);
        return ResponseEntity.ok(reviews);
    }
}