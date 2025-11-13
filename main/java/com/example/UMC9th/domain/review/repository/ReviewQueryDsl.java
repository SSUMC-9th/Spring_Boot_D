package com.example.UMC9th.domain.review.repository;

import com.example.UMC9th.domain.review.dto.ReviewResponseDTO;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {

    List<ReviewResponseDTO> searchReviews(Long userId, String storeName, Float rating);
}