package com.example.UMC9th.domain.review.service.query;

import com.example.UMC9th.domain.review.converter.ReviewConverter;
import com.example.UMC9th.domain.review.dto.ReviewResponseDTO;
import com.example.UMC9th.domain.review.entity.Review;
import com.example.UMC9th.domain.store.entity.Store;
import com.example.UMC9th.domain.store.exception.StoreException;
import com.example.UMC9th.domain.store.exception.code.StoreErrorCode;
import com.example.UMC9th.domain.store.repository.StoreRepository;
import com.example.UMC9th.domain.review.repository.ReviewRepository;
import com.example.UMC9th.domain.user.repository.UserRepository;
import com.example.UMC9th.domain.user.entity.User;
import com.example.UMC9th.domain.user.exception.UserException;
import com.example.UMC9th.domain.user.exception.code.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override
    public ReviewResponseDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    ){
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    @Override
    public ReviewResponseDTO.MyReviewPreViewListDTO findMyReviews(Long userId, Integer page) {

        // 1) 유저 존재 검증
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        // 2) 유저에 맞는 리뷰를 가져옴
        PageRequest pageRequest = PageRequest.of(page, 5);   // 페이지 사이즈 5는 취향대로

        // 3) 해당 유저가 쓴 리뷰들 페이징 조회
        Page<Review> result = reviewRepository.findAllByUser(user, pageRequest);

        // 4) 결과를 DTO변환
        return ReviewConverter.toMyReviewPreviewListDTO(result);
    }
}
