package com.umc9th.peter.domain.review.converter;

import com.umc9th.peter.domain.review.dto.ReviewResponse;
import com.umc9th.peter.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public class ReviewConverter {

    public static ReviewResponse.ReviewListDto toReviewListDto(
            Page<Review> result
    ) {
        List<ReviewResponse.ReviewDto> reviewDtos = result.stream()
                .map(ReviewResponse.ReviewDto::fromEntity)
                .toList();

        return ReviewResponse.ReviewListDto.builder()
                .reviewList(reviewDtos)
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

}
