package com.example.UMC9th.domain.review.service;
import com.example.UMC9th.domain.review.converter.ReviewConverter;
import com.example.UMC9th.domain.review.dto.ReviewReqDTO;
import com.example.UMC9th.domain.review.dto.ReviewResDTO;
import com.example.UMC9th.domain.review.entity.Review;
import com.example.UMC9th.domain.review.repository.ReviewRepository;
import com.example.UMC9th.domain.store.entity.Store;
import com.example.UMC9th.domain.store.exception.StoreException;
import com.example.UMC9th.domain.store.exception.code.StoreErrorCode;
import com.example.UMC9th.domain.store.repository.StoreRepository;
import com.example.UMC9th.domain.user.entity.User;
import com.example.UMC9th.domain.user.exception.UserException;
import com.example.UMC9th.domain.user.exception.code.UserErrorCode;
import com.example.UMC9th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ReviewResDTO.CreateDTO createReview(
            ReviewReqDTO.CreateDTO dto
    ) {
        // 사용자 검증
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        // 가게 검증
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));


        // DTO -> Entity
        Review review = ReviewConverter.toReview(dto, user, store);

        // 저장
        Review saved = reviewRepository.save(review);

        // Entity -> DTO
        return ReviewConverter.toCreateDTO(saved);
    }
}