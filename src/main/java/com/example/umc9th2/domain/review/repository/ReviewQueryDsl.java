package com.example.umc9th2.domain.review.repository;

import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import java.util.List;

public interface ReviewQueryDsl {
    List<ReviewResponseDto> findFilteredReviews(Long userId, String storeName, Float rating);
}

