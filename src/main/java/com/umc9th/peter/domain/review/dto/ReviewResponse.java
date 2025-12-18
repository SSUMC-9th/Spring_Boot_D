package com.umc9th.peter.domain.review.dto;

import com.umc9th.peter.domain.review.entity.Answer;
import com.umc9th.peter.domain.review.entity.Review;
import com.umc9th.peter.domain.store.dto.StoreResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponse {

    public record ReviewDto(
            Long id,
            String author,
            Integer star,
            String content,
            StoreResponse.StoreDto store,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            ReviewResponse.AnswerDto answer
    ) {

        public static ReviewDto fromEntity(Review review) {
            Answer answer = review.getAnswer();

            return new ReviewDto(
                    review.getId(),
                    review.getAuthor().getNickname(),
                    review.getStar(),
                    review.getContent(),
                    StoreResponse.StoreDto.fromEntity(review.getStore()),
                    review.getCreatedAt(),
                    review.getUpdatedAt(),
                    ReviewResponse.AnswerDto.fromEntity(answer)
            );
        }

    }

    @Builder
    public record ReviewListDto(
            List<ReviewDto> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

    public record AnswerDto(
            Long id,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {

        public static AnswerDto fromEntity(Answer answer) {
            if (answer == null) {
                return null;
            }
            return new AnswerDto(
                    answer.getId(),
                    answer.getContent(),
                    answer.getCreatedAt(),
                    answer.getUpdatedAt()
            );
        }

    }

}