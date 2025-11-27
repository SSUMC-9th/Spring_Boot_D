package com.example.umc9th2.domain.User.converter;

import com.example.umc9th2.domain.User.dto.UserReqDTO;
import com.example.umc9th2.domain.User.dto.UserResDTO;
import com.example.umc9th2.domain.User.entity.User;

public class UserConverter {

    // Entity -> DTO
    public static UserResDTO.JoinDTO toJoinDTO(User user) {
        return UserResDTO.JoinDTO.builder()
                .memberId(user.getUserId())
                .createAt(user.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static User toUser(UserReqDTO.JoinDTO dto) {

        return User.builder()
                .name(dto.name())
                .birth(dto.birth())
                .gender(dto.gender())

                // RabbitConnectionDetails.Address → String 변환
                .address(dto.address() != null
                        ? dto.address().toString()
                        : null)
                .specAddress(dto.specAddress())

                .build();
    }
}
