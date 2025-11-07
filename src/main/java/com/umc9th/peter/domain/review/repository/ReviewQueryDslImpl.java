package com.umc9th.peter.domain.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc9th.peter.domain.review.dto.ReviewSearchCondition;
import com.umc9th.peter.domain.review.entity.QReview;
import com.umc9th.peter.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    private final QReview review = QReview.review;

    @Override
    public List<Review> searchReviewsByConditions(ReviewSearchCondition conditions) {

        BooleanBuilder builder = new BooleanBuilder();
        if (conditions.memberId() != null) {
            builder.and(review.author.id.eq(conditions.memberId()));
        }
        if (conditions.storeId() != null) {
            builder.and(review.store.id.eq(conditions.storeId()));
        }
        if (conditions.star() != null) {
            builder.and(review.star.eq(conditions.star()));
        }

        return queryFactory
                .selectFrom(review)
                .leftJoin(review.author).fetchJoin()
                .leftJoin(review.store).fetchJoin()
                .leftJoin(review.answer).fetchJoin()
                .where(builder)
                .fetch();
    }

}
