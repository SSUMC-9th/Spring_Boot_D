package com.umc9th.peter.domain.review.dto;

public class ReviewRequest {

    public record ReviewDto(
            Long storeId,
            Integer star,
            String content
    ) {
    }

    public record SearchConditionDto(
            Long memberId,
            Long storeId,
            Integer star
    ) {

    }

}
