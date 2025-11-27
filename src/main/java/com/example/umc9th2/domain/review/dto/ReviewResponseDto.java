package com.example.umc9th2.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//내가 작성한 리뷰
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponseDto {

    private Long reviewId;        // 리뷰 ID
    private String content;       // 리뷰 내용
    private Integer score;          // 별점
    private String replyContent;  // 사장님 댓글 내용 (nullable)

}
