package com.example.UMC9th.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {
    public record CreateDTO(
            Long userId,
            Long storeId,
            Float reviewScore,
            String reviewComment,
            List<String> photoURLs
    ) {}
}
