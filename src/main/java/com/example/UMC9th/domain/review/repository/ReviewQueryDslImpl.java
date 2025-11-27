package com.example.UMC9th.domain.review.repository;

import com.example.UMC9th.domain.review.dto.ReviewResDTO;
import com.example.UMC9th.domain.review.entity.QReply;
import com.example.UMC9th.domain.review.entity.QReview;
import com.example.UMC9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    @Override
    public List<ReviewResDTO> searchReviews(Long userId, String storeName, Float rating) {

        // QueryFactory 생성
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        // Q클래스
        QReview review = QReview.review;
        QReply reply = QReply.reply;
        QStore store = QStore.store;

        // 동적 조건 생성
        BooleanBuilder builder = new BooleanBuilder();

        // 1) 내 리뷰만
        builder.and(review.user.userId.eq(userId));

        // 2) 가게명 필터
        if (storeName != null && !storeName.trim().isEmpty()) {
            builder.and(store.storeName.eq(storeName));
        }

        // 3) 별점 필터 (예: 3.0 → 3.0 ~ 3.9)
        if (rating != null) {
            builder.and(review.reviewValue.between(rating, rating + 0.9f));
        }

        // 쿼리 실행 & DTO 매핑
        return queryFactory
                .select(Projections.constructor(
                        ReviewResDTO.class,
                        review.reviewId,   // Long
                        review.reviewContent,    // String
                        review.reviewValue,      // Float
                        reply.reviewReply,    //
                        review.createdAt
                ))
                .from(review)
                .leftJoin(review.store, store)
                .leftJoin(review.replies, reply)
                .where(builder)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}