package com.example.umc9th2.domain.review.service.query;

import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import com.example.umc9th2.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewQueryService {
    List<ReviewResponseDto> getFilteredReviews(Long userId, String storeName, Float rating);
}

