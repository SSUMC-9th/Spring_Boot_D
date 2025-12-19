package com.example.umc9th2.domain.review.converter;

import com.example.umc9th2.domain.review.dto.ReviewRequestDto;
import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import com.example.umc9th2.domain.review.entity.Review;
import com.example.umc9th2.domain.store.entity.Store;
import com.example.umc9th2.domain.User.entity.User;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class ReviewConverter {

    // DTO → 엔티티
    public static Review toReview(ReviewRequestDto.CreateDTO dto, User user, Store store) {
        return Review.builder()
                .user(user)
                .store(store)
                .content(dto.content())
                .score(dto.score())
                .build();
    }

    // 엔티티 → 단일 리뷰 DTO
    public static ReviewResponseDto toResponseDto(Review review) {

        String replyContent = null;
        if (!review.getReplies().isEmpty()) {
            replyContent = review.getReplies().get(0).getContent();
        }

        return ReviewResponseDto.builder()
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .score(review.getScore())
                .replyContent(replyContent)
                .build();
    }

    public static ReviewResponseDto.ReviewPreViewListDTO toReviewPreviewListDTO(Page<Review> result) {

        // DTO 구조는 reviewList가 단일 DTO라서 첫 번째 리뷰만 넣는다
        ReviewResponseDto.ReviewPreViewDTO previewDTO = null;

        if (!result.getContent().isEmpty()) {
            // 첫 번째 요소만 DTO로 변환(전체 반환하게 다시)
            previewDTO = toReviewPreviewDTO(result.getContent().get(0));
        }

        return ReviewResponseDto.ReviewPreViewListDTO.builder()
                .reviewList(previewDTO)
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    // 엔티티 → PreviewDTO
    public static ReviewResponseDto.ReviewPreViewDTO toReviewPreviewDTO(Review review) {
        return ReviewResponseDto.ReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getName())
                .score(review.getScore().floatValue())       // Float 타입 맞춰줌
                .body(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    //내가 작성한 리뷰 목록
    public static ReviewResponseDto.ReviewPreViewListDTO toMyReviewListDTO(Page<Review> pageResult) {

        ReviewResponseDto.ReviewPreViewDTO previewDTO = pageResult.getContent().stream()
                .findFirst()
                .map(ReviewConverter::toMyReviewDTO)
                .orElse(null);

        return ReviewResponseDto.ReviewPreViewListDTO.builder()
                .reviewList(previewDTO)
                //스트림 사용해서 리뷰 여러 개 -> dto 여러 개 반환
                .listSize(pageResult.getSize())
                .totalPage(pageResult.getTotalPages())
                .totalElements(pageResult.getTotalElements())
                .isFirst(pageResult.isFirst())
                .isLast(pageResult.isLast())
                .build();
    }

    public static ReviewResponseDto.ReviewPreViewDTO toMyReviewDTO(Review review) {
        return ReviewResponseDto.ReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getName())
                .score(review.getScore().floatValue())
                .body(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

}
