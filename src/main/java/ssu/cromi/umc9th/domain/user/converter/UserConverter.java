package ssu.cromi.umc9th.domain.user.converter;

import ssu.cromi.umc9th.domain.user.dto.UserReqDTO;
import ssu.cromi.umc9th.domain.user.dto.UserResDTO;
import ssu.cromi.umc9th.domain.user.entity.User;

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
            UserReqDTO.JoinDTO dto
    ){
        return User.builder()
                .nickname(dto.nickname())
                .birthday(dto.birthday())
                .address(dto.address())
                .specAddress(dto.specAddress())
                .gender(dto.gender())
                .build();
    }
}
