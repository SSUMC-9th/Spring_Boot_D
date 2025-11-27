package com.umc9th.peter.domain.review.service;

import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.member.exception.MemberException;
import com.umc9th.peter.domain.member.exception.code.MemberErrorCode;
import com.umc9th.peter.domain.member.repository.MemberRepository;
import com.umc9th.peter.domain.review.converter.ReviewConverter;
import com.umc9th.peter.domain.review.dto.ReviewRequest;
import com.umc9th.peter.domain.review.dto.ReviewResponse;
import com.umc9th.peter.domain.review.entity.Review;
import com.umc9th.peter.domain.review.repository.ReviewRepository;
import com.umc9th.peter.domain.store.entity.Store;
import com.umc9th.peter.domain.store.exception.StoreException;
import com.umc9th.peter.domain.store.exception.code.StoreErrorCode;
import com.umc9th.peter.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResponse.ReviewListDto getReviews(
            Long memberId,
            Long storeId,
            Integer star,
            Integer page,
            Integer limit
    ) {
        ReviewRequest.SearchConditionDto condition = new ReviewRequest.SearchConditionDto(memberId, storeId, star);
        PageRequest pageRequest = PageRequest.of(page - 1, limit);

        Page<Review> reviews = reviewRepository.searchReviewsByConditions(condition, pageRequest);

        return ReviewConverter.toReviewListDto(reviews);
    }

    public ReviewResponse.ReviewDto writeReview(
            Long memberId,
            ReviewRequest.ReviewDto dto
    ) {
        Member author = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Review review = Review.builder()
                .author(author)
                .store(store)
                .star(dto.star())
                .content(dto.content())
                .build();
        reviewRepository.save(review);

        return ReviewResponse.ReviewDto.fromEntity(review);
    }

}
