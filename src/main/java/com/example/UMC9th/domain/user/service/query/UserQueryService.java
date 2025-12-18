package com.example.UMC9th.domain.user.service.query;

import com.example.UMC9th.domain.user.dto.UserReqDTO;
import com.example.UMC9th.domain.user.dto.UserResDTO;
import jakarta.validation.Valid;

public interface UserQueryService {
    UserResDTO.LoginDTO login(UserReqDTO.@Valid LoginDTO dto);
}
