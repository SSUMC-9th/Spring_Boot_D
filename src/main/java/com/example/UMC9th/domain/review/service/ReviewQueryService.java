package com.example.UMC9th.domain.review.service;

import com.example.UMC9th.domain.review.dto.ReviewResponseDTO;
import com.example.UMC9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    // 필터링 조건: 가게명 / 별점 (QueryDSL 기반)
    public List<ReviewResponseDTO> searchReviews(Long userId, String storeName, Float rating) {
        return reviewRepository.searchReviews(userId, storeName, rating);
    }
}