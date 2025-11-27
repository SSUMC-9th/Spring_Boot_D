package com.example.umc9th2.domain.review.service.query;

import com.example.umc9th2.domain.review.dto.ReviewResponseDto;
import com.example.umc9th2.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryService {

   // List<Review> searchReview(String filter, String type) throws Exception;
    //내가 작성한 리뷰 목록
   ReviewResponseDto.ReviewPreViewListDTO getMyReviews(Long userId, Integer page);
   //특정 가게 리뷰 목록
    ReviewResponseDto.ReviewPreViewListDTO findReview(String storeName, Integer page);
    //검색 필터링된 리뷰
    List<ReviewResponseDto> getFilteredReviews(Long userId, String storeName, Float rating);
}
