package com.example.UMC9th.domain.user.dto;

import com.example.UMC9th.domain.store.enums.Address;
import com.example.UMC9th.domain.user.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {
    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String specAddress,
            @Email
            String email, // 추가된 속성
            @NotBlank
            String password, // 추가된 속성
            List<Long> preferCategory
    ){}
    // 로그인
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
