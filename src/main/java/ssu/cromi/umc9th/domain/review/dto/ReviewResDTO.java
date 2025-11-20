package ssu.cromi.umc9th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    public record CreateDTO(
            Long reviewId,
            Long userId,
            Long storeId,
            Float score,
            String reviewText,
            LocalDateTime createdAt
    ) {}
}
