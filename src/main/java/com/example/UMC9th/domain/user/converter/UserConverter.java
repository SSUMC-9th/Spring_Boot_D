package com.example.UMC9th.domain.user.converter;

import com.example.UMC9th.domain.user.dto.UserReqDTO;
import com.example.UMC9th.domain.user.dto.UserResDTO;
import com.example.UMC9th.domain.user.entity.User;

public class UserConverter {
    // Entity -> DTO
    public static UserResDTO.JoinDTO toJoinDTO(
            User user
    ){
        return UserResDTO.JoinDTO.builder()
                .memberId(user.getUserId())
                .createdAt(user.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static User toUser(
            UserReqDTO.JoinDTO dto
    ){
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .birth(dto.birth().toString())
                .address(dto.address())
                .detailAddress(dto.specAddress())
                .gender(dto.gender())
                .build();
    }
}
