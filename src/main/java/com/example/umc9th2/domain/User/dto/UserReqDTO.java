package com.example.umc9th2.domain.User.dto;

import com.example.umc9th2.domain.User.enums.Gender;
import com.example.umc9th2.global.annotation.ExistFoods;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.LocalDate;
import java.util.List;

//public class UserReqDTO {
//
//    public record JoinDTO(
//            String name,
//            Gender gender,
//            LocalDate birth,
//            RabbitConnectionDetails.Address address,
//            String specAddress,
//            List<Long> preferCategory
//    ){}
//}
public class UserReqDTO {

    public record JoinDTO(
            @NotBlank
            String name,
            @Email
            String email, // 추가된 속성
            @NotBlank
            String password, // 추가된 속성
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            String address,
            @NotNull
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}
}
