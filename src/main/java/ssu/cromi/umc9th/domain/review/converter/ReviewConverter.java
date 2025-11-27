package ssu.cromi.umc9th.domain.review.converter;

import ssu.cromi.umc9th.domain.review.dto.ReviewReqDTO;
import ssu.cromi.umc9th.domain.review.dto.ReviewResDTO;
import ssu.cromi.umc9th.domain.review.entity.ReviewPictures;
import ssu.cromi.umc9th.domain.review.entity.UserReview;
import ssu.cromi.umc9th.domain.store.entity.Store;
import ssu.cromi.umc9th.domain.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // Entity -> DTO
    public static ReviewResDTO.CreateDTO toCreateDTO(UserReview review, List<String> photoURLs) {
        return ReviewResDTO.CreateDTO.builder()
                .reviewId(review.getId())
                .userId(review.getUser().getUserId())
                .storeId(review.getStore().getId())
                .score(review.getScore())
                .reviewText(review.getReviewText())
                .photoURLs(photoURLs)
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

    // photoURLs -> ReviewPictures List
    public static List<ReviewPictures> toReviewPictures(List<String> photoURLs, User user, Store store) {
        if (photoURLs == null || photoURLs.isEmpty()) {
            return List.of();
        }

        return photoURLs.stream()
                .map(url -> ReviewPictures.builder()
                        .user(user)
                        .store(store)
                        .imageUrl(url)
                        .build())
                .collect(Collectors.toList());
    }
}
