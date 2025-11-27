package ssu.cromi.umc9th.domain.user.service;

import ssu.cromi.umc9th.domain.user.dto.UserReqDTO;
import ssu.cromi.umc9th.domain.user.dto.UserResDTO;
import ssu.cromi.umc9th.domain.user.repository.UserRepository;

public interface UserCommandService {

    //회원가입
    UserResDTO.JoinDTO signup(
            UserReqDTO.JoinDTO dto
    );
}
