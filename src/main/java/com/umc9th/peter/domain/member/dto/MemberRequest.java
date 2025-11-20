package com.umc9th.peter.domain.member.dto;

import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.member.enums.AccountType;
import com.umc9th.peter.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberRequest {

    public record joinDto(
            String name,
            String nickname,
            Gender gender,
            LocalDate birthdate,
            String address,
            String addressDetail,
            AccountType type,
            List<Long> foodCategory
    ) {

        public static Member toEntity(joinDto dto) {
            return Member.builder()
                    .name(dto.name)
                    .nickname(dto.nickname)
                    .gender(dto.gender)
                    .birthdate(dto.birthdate)
                    .address(dto.address)
                    .addressDetail(dto.addressDetail)
                    .type(dto.type)
                    .build();
        }

    }

}