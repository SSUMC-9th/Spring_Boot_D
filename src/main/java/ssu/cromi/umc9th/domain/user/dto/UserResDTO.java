package ssu.cromi.umc9th.domain.user.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class UserResDTO {

    @Builder
    public record JoinDTO(
       Long userId,
       LocalDateTime createdAt
    ){}
}
