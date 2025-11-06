package com.umc9th.peter.domain.review.dto;

import com.umc9th.peter.domain.review.entity.Answer;

import java.time.LocalDateTime;

public record AnswerResponse(Long id, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {

    public static AnswerResponse fromEntity(Answer answer) {
        if (answer == null) {
            return null;
        }
        return new AnswerResponse(
                answer.getId(),
                answer.getContent(),
                answer.getCreatedAt(),
                answer.getUpdatedAt()
        );
    }

}
