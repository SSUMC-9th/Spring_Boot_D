package com.umc9th.peter.domain.review.repository;

import com.umc9th.peter.domain.review.dto.ReviewRequest;
import com.umc9th.peter.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryDsl {
    List<Review> searchReviewsByConditions(ReviewRequest.SearchConditionDto conditions);
}
