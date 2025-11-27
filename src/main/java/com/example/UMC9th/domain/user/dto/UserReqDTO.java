package com.example.UMC9th.domain.user.dto;

import com.example.UMC9th.domain.store.enums.Address;
import com.example.UMC9th.domain.user.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {
    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String specAddress,
            List<Long> preferCategory
    ){}

}
