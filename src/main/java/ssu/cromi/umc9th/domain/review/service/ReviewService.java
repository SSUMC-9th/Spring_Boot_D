package ssu.cromi.umc9th.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssu.cromi.umc9th.domain.review.dto.MyReviewResponseDto;
import ssu.cromi.umc9th.domain.review.dto.ReviewFilterDto;
import ssu.cromi.umc9th.domain.review.repository.UserReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final UserReviewRepository userReviewRepository;

    //내가 작성한 리뷰 조회 (동적 필터링)
    public Page<MyReviewResponseDto> getMyReviews(Long userId, ReviewFilterDto filter, Pageable pageable) {
        return userReviewRepository.findMyReviewsWithFilter(userId, filter, pageable);
    }
}