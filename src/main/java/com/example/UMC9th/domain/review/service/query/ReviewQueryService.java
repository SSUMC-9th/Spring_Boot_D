package com.example.UMC9th.domain.review.service.query;

import com.example.UMC9th.domain.review.dto.ReviewResponseDTO;

public interface ReviewQueryService {

    //가게 기반 리뷰
    ReviewResponseDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    );

    //내가 쓴 리뷰
    ReviewResponseDTO.MyReviewPreViewListDTO findMyReviews(
            Long userId,
            Integer page
    );

}
