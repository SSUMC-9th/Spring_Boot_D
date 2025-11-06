package com.example.umc9th2.domain.review.service;

import com.example.umc9th2.domain.User.repository.UserRepository;
import com.example.umc9th2.domain.review.entity.Review;
import com.example.umc9th2.domain.review.repository.ReviewRepository;
import com.example.umc9th2.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    // 리뷰 작성
    public void createReview(Long storeId, Long userId, String description, Float rating) {
        Review review = Review.builder()
                .store(storeRepository.getReferenceById(storeId))
                .user(userRepository.getReferenceById(userId))
                .content(description)
                .score(rating)
                .createdAt(LocalDateTime.now())
                .build();

        reviewRepository.save(review);
    }
}


