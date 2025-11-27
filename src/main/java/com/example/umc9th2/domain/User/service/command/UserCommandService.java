package com.example.umc9th2.domain.User.service.command;

import com.example.umc9th2.domain.User.dto.UserReqDTO;
import com.example.umc9th2.domain.User.dto.UserResDTO;

public interface UserCommandService {
    //회원가입
    UserResDTO.JoinDTO signUp(
            UserReqDTO.JoinDTO dto
    );
}
