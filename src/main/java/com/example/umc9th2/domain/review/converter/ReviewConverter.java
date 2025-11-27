package com.example.umc9th2.domain.review.converter;

import com.example.umc9th2.domain.review.dto.ReviewRequestDto;
import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import com.example.umc9th2.domain.review.entity.Review;
import com.example.umc9th2.domain.store.entity.Store;
import com.example.umc9th2.domain.User.entity.User;

public class ReviewConverter {

    // DTO → Entity
    public static Review toReview(ReviewRequestDto.CreateDTO dto, User user, Store store) {
        return Review.builder()
                .user(user)
                .store(store)
                .content(dto.content())
                .score(dto.score())
                .build();
    }

    // Entity → DTO(사장님 댓글 1개만 보기)
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
}
