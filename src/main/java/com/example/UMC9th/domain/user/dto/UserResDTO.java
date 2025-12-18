package com.example.UMC9th.domain.user.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class UserResDTO {
    @Builder
    public record JoinDTO(
      Long memberId,
      LocalDateTime createdAt
    ){}

    // 로그인
    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}
}
