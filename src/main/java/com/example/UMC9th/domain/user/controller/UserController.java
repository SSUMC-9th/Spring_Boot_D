package com.example.UMC9th.domain.user.controller;

import com.example.UMC9th.domain.user.dto.UserReqDTO;
import com.example.UMC9th.domain.user.dto.UserResDTO;
import com.example.UMC9th.domain.user.exception.code.UserSuccessCode;
import com.example.UMC9th.domain.user.service.command.UserCommandService;
import com.example.UMC9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;


    //회원가입
    @PostMapping("/sign-up")
    public ApiResponse <UserResDTO.JoinDTO> signUp(
            @RequestBody UserReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, userCommandService.signup(dto));
    }
}
