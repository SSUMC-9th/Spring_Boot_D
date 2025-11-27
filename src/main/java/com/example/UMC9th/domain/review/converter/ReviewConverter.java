package com.example.UMC9th.domain.review.converter;
import com.example.UMC9th.domain.review.dto.ReviewReqDTO;
import com.example.UMC9th.domain.review.dto.ReviewResDTO;
import com.example.UMC9th.domain.review.entity.Review;
import com.example.UMC9th.domain.store.entity.Store;
import com.example.UMC9th.domain.user.entity.User;

public class ReviewConverter {

    // DTO -> Entity
    public static Review toReview(
            ReviewReqDTO.CreateDTO dto,
            User user,
            Store store
    ) {
        return Review.builder()
                .reviewScore(dto.reviewScore())
                .reviewComment(dto.reviewComment())
                .photoURLs(dto.photoURLs())
                .user(user)
                .store(store)
                .build();
    }

    // Entity -> DTO
    public static ReviewResDTO.CreateDTO toCreateDTO(
            Review review
    ) {
        return new ReviewResDTO.CreateDTO(
                review.getId(),
                review.getStore().getId(),
                review.getUser().getId(),
                review.getReviewScore(),
                review.getReviewComment(),
                review.getPhotoURLs(),
                review.getCreatedAt()
        );
    }
}