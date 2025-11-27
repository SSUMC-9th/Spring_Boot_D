package com.umc9th.peter.domain.review.repository;

import com.umc9th.peter.domain.review.dto.ReviewRequest;
import com.umc9th.peter.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryDsl {

    Page<Review> searchReviewsByConditions(
            ReviewRequest.SearchConditionDto conditions,
            Pageable pageable
    );

}
