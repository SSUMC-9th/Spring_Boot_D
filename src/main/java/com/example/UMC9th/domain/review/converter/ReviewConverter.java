package com.example.UMC9th.domain.review.converter;
import com.example.UMC9th.domain.review.dto.ReviewReqDTO;
import com.example.UMC9th.domain.review.dto.ReviewResDTO;
import com.example.UMC9th.domain.review.dto.ReviewResponseDTO;
import com.example.UMC9th.domain.review.entity.Review;
import com.example.UMC9th.domain.store.entity.Store;
import com.example.UMC9th.domain.user.entity.User;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class ReviewConverter {

    // DTO -> Entity
    public static Review toReview(
            ReviewReqDTO.CreateDTO dto,
            User user,
            Store store
    ) {
        return Review.builder()
                .reviewValue(dto.reviewScore())      // 점수 → reviewValue
                .reviewContent(dto.reviewComment())  // 내용 → reviewContent
                // photoURLs 필드가 엔티티에 없으니 일단 제거 (필요하면 엔티티에 컬럼 추가)
                .user(user)
                .store(store)
                .build();
    }

    // Entity -> DTO (리뷰 생성 응답)
    public static ReviewResDTO.CreateDTO toCreateDTO(
            Review review
    ) {
        return new ReviewResDTO.CreateDTO(
                review.getReviewId(),          // PK
                review.getStore().getStoreId(),     // 가게 id
                review.getUser().getUserId(),      // 유저 id
                review.getReviewValue(),       // 점수
                review.getReviewContent(),     // 내용
                null,                          // photoURLs (엔티티에 없으니 일단 null 또는 DTO에서 뺄지 결정)
                review.getCreatedAt()
        );
    }

    // Page<Review> → 목록 DTO
    public static ReviewResponseDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ) {
        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .reviewList(
                        result.getContent().stream()
                                .map(ReviewConverter::toReviewPreviewDTO)
                                .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    // Review 한 건 → 프리뷰 DTO
    public static ReviewResponseDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ) {
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getName())
                .score(review.getReviewValue())
                .body(review.getReviewContent())
                .createdAt(review.getCreatedAt().toLocalDate())  // LocalDateTime → LocalDate
                .build();
    }

    //내가 쓴 리뷰 목록용
    public static ReviewResponseDTO.MyReviewPreViewListDTO toMyReviewPreviewListDTO(
            Page<Review> result
    ) {
        return ReviewResponseDTO.MyReviewPreViewListDTO.builder()
                .reviewList(
                        result.getContent().stream()
                                .map(ReviewConverter::toMyReviewPreviewDTO)
                                .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResponseDTO.MyReviewPreViewDTO toMyReviewPreviewDTO(
            Review review
    ) {
        return ReviewResponseDTO.MyReviewPreViewDTO.builder()
                .storeName(review.getStore().getStoreName())        // 가게 이름
                .score(review.getReviewValue())                // 점수
                .body(review.getReviewContent())               // 내용
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}