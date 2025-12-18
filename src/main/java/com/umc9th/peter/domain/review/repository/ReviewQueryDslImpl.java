package com.umc9th.peter.domain.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc9th.peter.domain.review.dto.ReviewRequest;
import com.umc9th.peter.domain.review.entity.QReview;
import com.umc9th.peter.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    private final QReview review = QReview.review;

    @Override
    public Page<Review> searchReviewsByConditions(
            ReviewRequest.SearchConditionDto conditions,
            Pageable pageable
    ) {

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

        List<Review> reviews = queryFactory
                .selectFrom(review)
                .leftJoin(review.author).fetchJoin()
                .leftJoin(review.store).fetchJoin()
                .leftJoin(review.answer).fetchJoin()
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        long count = Optional.ofNullable(queryFactory
                        .select(review.count())
                        .from(review)
                        .where(builder)
                        .fetchOne())
                .orElse(0L);

        return new PageImpl<>(reviews, pageable, count);
    }

}
