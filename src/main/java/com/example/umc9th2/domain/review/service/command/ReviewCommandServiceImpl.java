package com.example.umc9th2.domain.review.service.command;

import com.example.umc9th2.domain.User.entity.User;
import com.example.umc9th2.domain.User.repository.UserRepository;
import com.example.umc9th2.domain.review.converter.ReviewConverter;
import com.example.umc9th2.domain.review.dto.ReviewRequestDto;
import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import com.example.umc9th2.domain.review.entity.Review;
import com.example.umc9th2.domain.review.repository.ReviewRepository;
import com.example.umc9th2.domain.store.entity.Store;
import com.example.umc9th2.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Override
    public ReviewResponseDto createReview(Long storeId, ReviewRequestDto.CreateDTO dto) {

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        Review review = ReviewConverter.toReview(dto, user, store);

        reviewRepository.save(review);

        return ReviewConverter.toResponseDto(review);
    }
}
