package com.umc9th.peter.domain.member.dto;

import com.umc9th.peter.domain.member.entity.Member;
import com.umc9th.peter.domain.member.enums.AccountType;
import com.umc9th.peter.domain.member.enums.Gender;
import com.umc9th.peter.global.annotation.ExistFoodCategories;
import com.umc9th.peter.global.auth.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public class MemberRequest {

    public record joinDto(
            String name,
            String nickname,
            @Email String email,
            @NotBlank String password,
            Gender gender,
            LocalDate birthdate,
            String address,
            String addressDetail,
            AccountType type,
            @ExistFoodCategories List<Long> foodCategory
    ) {

        public static Member toEntity(
                joinDto dto,
                String password,
                Role role) {
            return Member.builder()
                    .name(dto.name)
                    .nickname(dto.nickname)
                    .email(dto.email)
                    .password(password)
                    .role(role)
                    .gender(dto.gender)
                    .birthdate(dto.birthdate)
                    .address(dto.address)
                    .addressDetail(dto.addressDetail)
                    .type(dto.type)
                    .build();
        }

    }

    public record loginDto(
            @Email String email,
            @NotBlank String password
    ) {

    }

}