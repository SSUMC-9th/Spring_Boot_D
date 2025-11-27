package ssu.cromi.umc9th.domain.review.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ssu.cromi.umc9th.domain.review.converter.ReviewConverter;
import ssu.cromi.umc9th.domain.review.dto.ReviewResDTO;
import ssu.cromi.umc9th.domain.review.entity.UserReview;
import ssu.cromi.umc9th.domain.review.repository.UserReviewRepository;
import ssu.cromi.umc9th.domain.store.entity.Store;
import ssu.cromi.umc9th.domain.store.exception.StoreException.StoreException;
import ssu.cromi.umc9th.domain.store.exception.code.StoreErrorCode;
import ssu.cromi.umc9th.domain.store.repository.StoreRepository;
import ssu.cromi.umc9th.domain.user.entity.User;
import ssu.cromi.umc9th.domain.user.exception.UserException.UserException;
import ssu.cromi.umc9th.domain.user.exception.code.UserErrorCode;
import ssu.cromi.umc9th.domain.user.repository.UserRepository;
import ssu.cromi.umc9th.global.apiPayload.code.GeneralErrorCode;
import ssu.cromi.umc9th.global.apiPayload.exception.GeneralException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService{

    private final StoreRepository storeRepository;
    private final UserReviewRepository userReviewRepository;
    private final UserRepository userRepository;

    @Override
    public List<UserReview> searchReview(String filter, String type) throws Exception {
        return List.of();
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    ){
        Store store = storeRepository.findByStoreName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<UserReview> result = userReviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    @Override
    public ReviewResDTO.UserReviewListDTO getUserReviews(Long userId, String storeName, Integer page) {
        // 페이지 유효성 검증 (1 이상)
        if (page < 1) {
            throw new GeneralException(GeneralErrorCode.INVALID_PAGE);
        }

        // 사용자 존재 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        // 가게 존재 확인
        Store store = storeRepository.findByStoreName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 페이징 처리 (page는 1부터 시작하므로 -1, 한 페이지에 10개씩)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<UserReview> result = userReviewRepository.findAllByUserAndStore(user, store, pageRequest);

        return ReviewConverter.toUserReviewListDTO(result);
    }
}
