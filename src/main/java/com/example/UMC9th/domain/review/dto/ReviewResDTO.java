package com.example.UMC9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResDTO
{
    public record CreateDTO(
            Long reviewId,
            Long storeId,
            Long userId,
            Float reviewScore,
            String reviewComment,
            List<String> photoURLs,
            LocalDateTime createdAt
    ) {}

}
