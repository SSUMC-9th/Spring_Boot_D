package com.example.umc9th2.domain.User.service.query;

import com.example.umc9th2.domain.User.dto.UserReqDTO;
import com.example.umc9th2.domain.User.dto.UserResDTO;
import jakarta.validation.Valid;

public interface UserQueryService {
    UserResDTO.LoginDTO login(UserReqDTO.LoginDTO dto);
}

