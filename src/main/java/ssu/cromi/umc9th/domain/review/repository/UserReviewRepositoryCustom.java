package ssu.cromi.umc9th.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssu.cromi.umc9th.domain.review.dto.MyReviewResponseDto;
import ssu.cromi.umc9th.domain.review.dto.ReviewFilterDto;

public interface UserReviewRepositoryCustom {
    /**
     * 내가 작성한 리뷰 조회 (동적 필터링 지원)
     * @param userId 사용자 ID
     * @param filter 필터 조건 (가게ID, 별점 범위)
     * @param pageable 페이징 정보
     * @return 필터링된 리뷰 목록
     */
    Page<MyReviewResponseDto> findMyReviewsWithFilter(Long userId, ReviewFilterDto filter, Pageable pageable);
}
