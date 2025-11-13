package ssu.cromi.umc9th.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileDto {
    private Long id;
    private String nickname;
    private String email;
    private String phone;
    private Long userPoint;
}