package ssu.cromi.umc9th.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssu.cromi.umc9th.domain.review.converter.ReviewConverter;
import ssu.cromi.umc9th.domain.review.dto.MyReviewResponseDto;
import ssu.cromi.umc9th.domain.review.dto.ReviewFilterDto;
import ssu.cromi.umc9th.domain.review.dto.ReviewReqDTO;
import ssu.cromi.umc9th.domain.review.dto.ReviewResDTO;
import ssu.cromi.umc9th.domain.review.entity.ReviewPictures;
import ssu.cromi.umc9th.domain.review.entity.UserReview;
import ssu.cromi.umc9th.domain.review.exception.ReviewException.ReviewException;
import ssu.cromi.umc9th.domain.review.exception.code.ReviewErrorCode;
import ssu.cromi.umc9th.domain.review.repository.ReviewPicturesRepository;
import ssu.cromi.umc9th.domain.review.repository.UserReviewRepository;
import ssu.cromi.umc9th.domain.store.entity.Store;
import ssu.cromi.umc9th.domain.store.exception.StoreException.StoreException;
import ssu.cromi.umc9th.domain.store.exception.code.StoreErrorCode;
import ssu.cromi.umc9th.domain.store.repository.StoreRepository;
import ssu.cromi.umc9th.domain.user.entity.User;
import ssu.cromi.umc9th.domain.user.exception.UserException.UserException;
import ssu.cromi.umc9th.domain.user.exception.code.UserErrorCode;
import ssu.cromi.umc9th.domain.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final UserReviewRepository userReviewRepository;
    private final ReviewPicturesRepository reviewPicturesRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    //내가 작성한 리뷰 조회 (동적 필터링)
    public Page<MyReviewResponseDto> getMyReviews(Long userId, ReviewFilterDto filter, Pageable pageable) {
        return userReviewRepository.findMyReviewsWithFilter(userId, filter, pageable);
    }

    //리뷰 추가
    @Transactional
    public ReviewResDTO.CreateDTO createReview(ReviewReqDTO.CreateDTO dto) {
        // 별점 유효성 검사
        if (dto.score() < 0.0f || dto.score() > 5.0f) {
            throw new ReviewException(ReviewErrorCode.INVALID_SCORE);
        }

        // 사용자 존재 확인
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        // 가게 존재 확인
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 리뷰 엔티티 생성 및 저장
        UserReview review = ReviewConverter.toUserReview(dto, user, store);
        userReviewRepository.save(review);

        // 리뷰 사진 저장
        List<ReviewPictures> reviewPictures = ReviewConverter.toReviewPictures(dto.photoURLs(), user, store);
        reviewPicturesRepository.saveAll(reviewPictures);

        // 응답 DTO 반환
        return ReviewConverter.toCreateDTO(review, dto.photoURLs());
    }
}