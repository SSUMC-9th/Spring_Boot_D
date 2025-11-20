package com.umc9th.peter.domain.member.dto;

import com.umc9th.peter.domain.member.entity.Member;

import java.time.LocalDateTime;

public class MemberResponse {

    public record joinDto(
            Long memberId,
            LocalDateTime createdAt
    ) {

        public static joinDto fromEntity(Member member) {
            return new joinDto(
                    member.getId(),
                    member.getCreatedAt()
            );
        }

    }

}
