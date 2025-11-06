package com.example.umc9th2.domain.review.service;

import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import com.example.umc9th2.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    //필터링 조건: 가게명 / 별점 (QueryDSL 기반)
    public List<ReviewResponseDto> getFilteredReviews(Long userId, String storeName, Float rating) {
        return reviewRepository.findFilteredReviews(userId, storeName, rating);
    }
}
