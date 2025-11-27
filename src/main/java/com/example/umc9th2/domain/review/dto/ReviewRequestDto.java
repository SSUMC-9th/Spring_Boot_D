package com.example.umc9th2.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewRequestDto {

    public record CreateDTO(
            @NotNull(message = "회원 ID는 필수입니다.")
            Long userId,

            @NotBlank(message = "리뷰 내용은 필수입니다.")
            String content,

            @NotNull(message = "평점은 필수입니다.")
            @Min(value = 1, message = "최소 평점은 1점입니다.")
            @Max(value = 5, message = "최대 평점은 5점입니다.")
            Integer score
    ) {}
}
