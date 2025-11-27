package com.example.UMC9th.domain.user.service.command;

import com.example.UMC9th.domain.user.dto.UserReqDTO;
import com.example.UMC9th.domain.user.dto.UserResDTO;

public interface UserCommandService {

    //회원가입
    UserResDTO.JoinDTO signup(
            UserReqDTO.JoinDTO dto
    );
}
