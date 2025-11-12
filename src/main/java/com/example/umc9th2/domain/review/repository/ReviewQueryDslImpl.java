package com.example.umc9th2.domain.review.repository;

import jakarta.persistence.EntityManager;

import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import com.example.umc9th2.domain.review.entity.QReply;
import com.example.umc9th2.domain.review.entity.QReview;
import com.example.umc9th2.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
//리뷰 사진 고려X
@Service
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    //리뷰 검색
    @Override
    public List<ReviewResponseDto> findFilteredReviews(Long userId, String storeName, Float rating) {

        // QueryFactory 생성
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        // Q 클래스 선언
        QReview review = QReview.review;//리뷰
        QReply reply = QReply.reply;//답글
        QStore store = QStore.store;//가게

        // 동적 조건 builder
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(review.user.userId.eq(userId));//사용자가 작성한 리뷰 필터링

        //가게명이 입력된 경우 해당 가게만 필터링
        if (storeName != null && !storeName.isEmpty()) {
            builder.and(store.name.eq(storeName));
        }

        //별점이 입력된 경우
        if (rating != null) {
            // 3점대 → 3.0~3.9
            builder.and(review.score.between(rating, rating + 0.9f));
        }

        Predicate predicate = builder.getValue();

        // QueryDSL 쿼리 실행
        return queryFactory
                .select(Projections.constructor(ReviewResponseDto.class,
                        review.reviewId,
                        review.content,
                        review.score,
                        reply.content
                ))
                .from(review)
                .leftJoin(review.replies, reply)//답글이 없어도 리뷰는 나와야해서 left join
                .leftJoin(review.store, store)//리뷰와 연결된 가게
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}

