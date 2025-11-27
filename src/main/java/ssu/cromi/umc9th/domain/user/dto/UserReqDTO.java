package ssu.cromi.umc9th.domain.user.dto;

import ssu.cromi.umc9th.domain.user.enums.Gender;
import ssu.cromi.umc9th.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {
    public record JoinDTO(
            String nickname,
            Gender gender,
            LocalDate birthday,
            String address,
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}
}
