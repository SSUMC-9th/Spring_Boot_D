package ssu.cromi.umc9th.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ssu.cromi.umc9th.domain.user.enums.Gender;
import ssu.cromi.umc9th.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {
    public record JoinDTO(
            @NotBlank
            String nickname,
            @Email
            String email,
            @NotBlank
            String password,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birthday,
            @NotNull
            String address,
            @NotNull
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}
    public record LoginDTO(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}
}
