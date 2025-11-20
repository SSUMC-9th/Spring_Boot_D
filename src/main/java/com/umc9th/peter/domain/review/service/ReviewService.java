package com.umc9th.peter.domain.review.service;

import com.umc9th.peter.domain.review.dto.ReviewRequest;
import com.umc9th.peter.domain.review.dto.ReviewResponse;
import com.umc9th.peter.domain.review.entity.Review;
import com.umc9th.peter.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponse.ReviewDto> getReviews(
            Long memberId,
            Long storeId,
            Integer star
    ) {
        ReviewRequest.SearchConditionDto condition = new ReviewRequest.SearchConditionDto(memberId, storeId, star);

        List<Review> reviewList = reviewRepository.searchReviewsByConditions(condition);

        return reviewList.stream().map(ReviewResponse.ReviewDto::fromEntity).toList();
    }

}
