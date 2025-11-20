package com.example.umc9th2.domain.review.service.command;

import com.example.umc9th2.domain.review.dto.ReviewRequestDto;
import com.example.umc9th2.domain.review.dto.ReviewResponseDto;

public interface ReviewCommandService {
    ReviewResponseDto createReview(Long storeId, ReviewRequestDto.CreateDTO dto);//리뷰작성
}

