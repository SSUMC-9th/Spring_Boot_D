package ssu.cromi.umc9th.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ssu.cromi.umc9th.domain.user.dto.UserReqDTO;
import ssu.cromi.umc9th.domain.user.dto.UserResDTO;
import ssu.cromi.umc9th.domain.user.exception.code.UserSuccessCode;
import ssu.cromi.umc9th.domain.user.service.UserCommandService;
import ssu.cromi.umc9th.domain.user.service.UserQueryService;
import ssu.cromi.umc9th.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;


    @PostMapping("/sign-up")
    public ApiResponse<UserResDTO.JoinDTO> signUp(
            @RequestBody @Valid UserReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, userCommandService.signup(dto));
    }

    @PostMapping("/login")
    public ApiResponse<UserResDTO.LoginDTO> login(
            @RequestBody @Valid UserReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, userQueryService.login(dto));
    }
}
