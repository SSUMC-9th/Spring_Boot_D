package ssu.cromi.umc9th.domain.review.dto;

public class ReviewReqDTO {
    public record CreateDTO(
            Long userId,
            Long storeId,
            Float score,
            String reviewText
    ) {}
}
