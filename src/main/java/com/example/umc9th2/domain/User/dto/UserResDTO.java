package com.example.umc9th2.domain.User.dto;


import lombok.Builder;

import java.time.LocalDateTime;

public class UserResDTO {
    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){}
}
