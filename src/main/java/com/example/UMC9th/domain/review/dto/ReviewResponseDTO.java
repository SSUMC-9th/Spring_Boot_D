package com.example.UMC9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponseDTO
{
    private Long reviewId; //리뷰 아이디
    private String reviewComment; //리뷰 내용
    private Float reviewScore; //리뷰 별점
    private String reviewReply; //사장님 코멘트
    private LocalDateTime createdAt; //리뷰 생성시점

}
