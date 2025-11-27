package com.example.UMC9th.domain.review.repository;

import com.example.UMC9th.domain.review.dto.ReviewResDTO;

import java.util.List;

public interface ReviewQueryDsl {

    List<ReviewResDTO> searchReviews(Long userId, String storeName, Float rating);
}