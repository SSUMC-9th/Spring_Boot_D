package ssu.cromi.umc9th.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyReviewResponseDto {
    private Long reviewId;
    private String storeName;
    private Float score;
    private String reviewText;
    private String ownerComment;
    private LocalDateTime createdAt;

    @QueryProjection
    public MyReviewResponseDto(Long reviewId, String storeName, Float score,
                               String reviewText, String ownerComment, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.storeName = storeName;
        this.score = score;
        this.reviewText = reviewText;
        this.ownerComment = ownerComment;
        this.createdAt = createdAt;
    }
}