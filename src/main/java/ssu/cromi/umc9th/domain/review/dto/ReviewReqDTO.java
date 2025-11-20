package ssu.cromi.umc9th.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {
    public record CreateDTO(
            Long userId,
            Long storeId,
            Float score,
            String reviewText,
            List<String> photoURLs
    ) {}
}
