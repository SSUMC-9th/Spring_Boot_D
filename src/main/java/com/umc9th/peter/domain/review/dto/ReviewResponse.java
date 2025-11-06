package com.umc9th.peter.domain.review.dto;

import com.umc9th.peter.domain.review.entity.Answer;
import com.umc9th.peter.domain.review.entity.Review;

import java.time.LocalDateTime;

public record ReviewResponse(Long id, String author, Integer star, String content, LocalDateTime createdAt,
                             LocalDateTime updatedAt, AnswerResponse answer) {

    public static ReviewResponse fromEntity(Review review) {
        Answer answer = review.getAnswer();

        return new ReviewResponse(
                review.getId(),
                review.getAuthor().getNickname(),
                review.getStar(),
                review.getContent(),
                review.getCreatedAt(),
                review.getUpdatedAt(),
                AnswerResponse.fromEntity(answer)
        );
    }

}
