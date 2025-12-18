package com.example.umc9th2.domain.review.service.query;

import com.example.umc9th2.domain.review.converter.ReviewConverter;
import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import com.example.umc9th2.domain.review.entity.Review;
import com.example.umc9th2.domain.review.repository.ReviewRepository;
import com.example.umc9th2.domain.store.entity.Store;
import com.example.umc9th2.domain.store.repository.StoreRepository;
import com.example.umc9th2.domain.store.exception.StoreException;
import com.example.umc9th2.domain.store.exception.code.StoreErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    private static final int PAGE_SIZE = 10;

    @Override
    public ReviewResponseDto.ReviewPreViewListDTO findReview(String storeName, Integer page) {

        // 가게 검색 + 예외 발생시 처리
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 페이징 생성(1번 페이지 = index = 0)
        PageRequest pageRequest = PageRequest.of(page, 5);

        // 가게 기준 리뷰 조회
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        // DTO 변환
        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    @Override
    public List<ReviewResponseDto> getFilteredReviews(Long userId, String storeName, Float rating) {
        return reviewRepository.findFilteredReviews(userId, storeName, rating);
    }


    //내가 작성한 리뷰 조회
    //page는 1이상 0, 음수 고려 x
    //사용자의 모든 리뷰를 가져오고 컨버터로 dto변환
    @Override
    public ReviewResponseDto.ReviewPreViewListDTO getMyReviews(Long userId, Integer page) {

        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE);

        Page<Review> result = reviewRepository.findAllByUser_UserId(userId, pageRequest);

        return ReviewConverter.toMyReviewListDTO(result);
    }
}
