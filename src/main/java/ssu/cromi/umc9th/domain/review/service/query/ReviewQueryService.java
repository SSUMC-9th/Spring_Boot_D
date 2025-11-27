package ssu.cromi.umc9th.domain.review.service.query;

import ssu.cromi.umc9th.domain.review.dto.ReviewResDTO;
import ssu.cromi.umc9th.domain.review.entity.UserReview;

import java.util.List;

public interface ReviewQueryService {

    List<UserReview> searchReview(
            String filter,
            String type
    )throws Exception;

    ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    );

    ReviewResDTO.UserReviewListDTO getUserReviews(
            Long userId,
            String storeName,
            Integer page
    );
}
