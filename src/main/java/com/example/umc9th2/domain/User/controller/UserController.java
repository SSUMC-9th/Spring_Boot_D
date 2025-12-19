package com.example.umc9th2.domain.User.controller;

import com.example.umc9th2.domain.User.dto.UserReqDTO;
import com.example.umc9th2.domain.User.dto.UserResDTO;
import com.example.umc9th2.domain.User.exception.code.UserSuccessCode;
import com.example.umc9th2.domain.User.repository.UserRepository;
import com.example.umc9th2.domain.User.service.command.UserCommandService;
import com.example.umc9th2.domain.User.service.query.UserQueryService;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<UserResDTO.JoinDTO> signUp(
            @RequestBody @Valid UserReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, userCommandService.signUp(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<UserResDTO.LoginDTO> login(
            @RequestBody @Valid UserReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(
                UserSuccessCode.FOUND,
                userQueryService.login(dto)
        );

    }
}
