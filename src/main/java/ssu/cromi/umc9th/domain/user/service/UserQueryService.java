package ssu.cromi.umc9th.domain.user.service;

import ssu.cromi.umc9th.domain.user.dto.UserReqDTO;
import ssu.cromi.umc9th.domain.user.dto.UserResDTO;

public interface UserQueryService {

    // 로그인
    UserResDTO.LoginDTO login(
            UserReqDTO.LoginDTO dto
    );
}