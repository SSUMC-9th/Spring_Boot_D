package com.example.umc9th2.domain.review.service.query;

import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import com.example.umc9th2.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewResponseDto> getFilteredReviews(Long userId, String storeName, Float rating) {
        return reviewRepository.findFilteredReviews(userId, storeName, rating);
    }
}

