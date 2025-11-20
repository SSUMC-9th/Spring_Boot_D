package com.umc9th.peter.domain.review.dto;

public class ReviewRequest {

    public record SearchConditionDto(
            Long memberId,
            Long storeId,
            Integer star
    ) {

    }

}
