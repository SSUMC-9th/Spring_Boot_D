package ssu.cromi.umc9th.domain.review.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import ssu.cromi.umc9th.domain.review.dto.MyReviewResponseDto;
import ssu.cromi.umc9th.domain.review.dto.QMyReviewResponseDto;
import ssu.cromi.umc9th.domain.review.dto.ReviewFilterDto;

import java.util.List;

import static ssu.cromi.umc9th.domain.review.entity.QUserReview.userReview;
import static ssu.cromi.umc9th.domain.store.entity.QStore.store;

@RequiredArgsConstructor
public class UserReviewRepositoryImpl implements UserReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MyReviewResponseDto> findMyReviewsWithFilter(Long userId, ReviewFilterDto filter, Pageable pageable) {
        // 메인 쿼리: 리뷰 데이터 조회
        List<MyReviewResponseDto> content = queryFactory
                .select(new QMyReviewResponseDto(
                        userReview.id,
                        store.storeName,
                        userReview.score,
                        userReview.reviewText,
                        userReview.ownerComment,
                        userReview.createdAt
                ))
                .from(userReview)
                .join(userReview.store, store)
                .where(
                        userIdEq(userId),
                        storeIdEq(filter.getStoreId()),
                        scoreGoe(filter.getMinScore()),
                        scoreLoe(filter.getMaxScore())
                )
                .orderBy(userReview.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 카운트 쿼리: 전체 개수 조회 (성능 최적화를 위해 분리)
        JPAQuery<Long> countQuery = queryFactory
                .select(userReview.count())
                .from(userReview)
                .where(
                        userIdEq(userId),
                        storeIdEq(filter.getStoreId()),
                        scoreGoe(filter.getMinScore()),
                        scoreLoe(filter.getMaxScore())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    // 동적 조건 메서드들
    private BooleanExpression userIdEq(Long userId) {
        return userId != null ? userReview.user.id.eq(userId) : null;
    }

    private BooleanExpression storeIdEq(Long storeId) {
        return storeId != null ? userReview.store.id.eq(storeId) : null;
    }

    private BooleanExpression scoreGoe(Float minScore) {
        return minScore != null ? userReview.score.goe(minScore) : null;
    }

    private BooleanExpression scoreLoe(Float maxScore) {
        return maxScore != null ? userReview.score.loe(maxScore) : null;
    }
}