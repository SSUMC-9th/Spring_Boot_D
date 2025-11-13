package com.umc9th.peter.domain.review.dto;

public record ReviewSearchCondition(
        Long memberId,
        Long storeId,
        Integer star
) {
    
}
