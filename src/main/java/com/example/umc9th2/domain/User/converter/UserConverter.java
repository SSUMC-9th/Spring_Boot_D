package com.example.umc9th2.domain.User.converter;

import com.example.umc9th2.domain.User.dto.UserReqDTO;
import com.example.umc9th2.domain.User.dto.UserResDTO;
import com.example.umc9th2.domain.User.entity.User;
import com.example.umc9th2.domain.User.enums.OauthProvider;
import com.example.umc9th2.global.auth.enums.Role;

public class UserConverter {

    // Entity -> DTO
    public static UserResDTO.JoinDTO toJoinDTO(User user) {
        return UserResDTO.JoinDTO.builder()
                .memberId(user.getUserId())
                .createAt(user.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static User toUser(
            UserReqDTO.JoinDTO dto,
            String password,
            Role role
    ) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())      // 추가
                .password(password)     // 추가 (암호화된 비밀번호)
                .role(role)             // 추가
                .birth(dto.birth())
                .gender(dto.gender())
                .address(
                        dto.address() != null
                                ? dto.address().toString()
                                : null
                )
                .specAddress(dto.specAddress())
                .oauthProvider(OauthProvider.LOCAL)
                .build();
    }

    public static UserResDTO.LoginDTO LoginDTO(User user, String accessToken) {
        return UserResDTO.LoginDTO.builder()
                .userId(user.getUserId())
                .accessToken(accessToken)
                .build();
    }

}
