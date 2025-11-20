package ssu.cromi.umc9th.domain.review.converter;

import ssu.cromi.umc9th.domain.review.dto.ReviewReqDTO;
import ssu.cromi.umc9th.domain.review.dto.ReviewResDTO;
import ssu.cromi.umc9th.domain.review.entity.UserReview;
import ssu.cromi.umc9th.domain.store.entity.Store;
import ssu.cromi.umc9th.domain.user.entity.User;

public class ReviewConverter {

    // Entity -> DTO
    public static ReviewResDTO.CreateDTO toCreateDTO(UserReview review) {
        return ReviewResDTO.CreateDTO.builder()
                .reviewId(review.getId())
                .userId(review.getUser().getUserId())
                .storeId(review.getStore().getId())
                .score(review.getScore())
                .reviewText(review.getReviewText())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static UserReview toUserReview(ReviewReqDTO.CreateDTO dto, User user, Store store) {
        return UserReview.builder()
                .user(user)
                .store(store)
                .score(dto.score())
                .reviewText(dto.reviewText())
                .build();
    }
}
