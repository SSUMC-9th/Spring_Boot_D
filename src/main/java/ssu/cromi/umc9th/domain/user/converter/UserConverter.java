package ssu.cromi.umc9th.domain.user.converter;

import ssu.cromi.umc9th.domain.user.dto.UserReqDTO;
import ssu.cromi.umc9th.domain.user.dto.UserResDTO;
import ssu.cromi.umc9th.domain.user.entity.User;
import ssu.cromi.umc9th.global.auth.enums.Role;

import java.lang.reflect.Member;

public class UserConverter {
    //Entity -> DTO
    public static UserResDTO.JoinDTO toJoinDTO(
            User user
    ){
        return UserResDTO.JoinDTO.builder()
                .userId(user.getUserId())
                .createdAt(user.getCreatedAt())
                .build();
    }

    //DTO -> Entity
    public static User toUser(
            UserReqDTO.JoinDTO dto,
            String password,
            Role role
    ){
        return User.builder()
                .nickname(dto.nickname())
                .email(dto.email())
                .password(password)
                .role(role)
                .birthday(dto.birthday())
                .address(dto.address())
                .specAddress(dto.specAddress())
                .gender(dto.gender())
                .build();
    }

    public static UserResDTO.LoginDTO toLoginDTO(User user, String accessToken) {
        return UserResDTO.LoginDTO.builder()
                .userId(user.getUserId())
                .accessToken(accessToken)
                .build();
    }
}
