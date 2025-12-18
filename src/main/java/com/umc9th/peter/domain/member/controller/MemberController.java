package com.umc9th.peter.domain.member.controller;

import com.umc9th.peter.domain.member.dto.MemberRequest;
import com.umc9th.peter.domain.member.dto.MemberResponse;
import com.umc9th.peter.domain.member.enums.MissionStatus;
import com.umc9th.peter.domain.member.exception.code.MemberSuccessCode;
import com.umc9th.peter.domain.member.service.MemberService;
import com.umc9th.peter.domain.mission.exception.code.MissionSuccessCode;
import com.umc9th.peter.global.annotation.PageNumber;
import com.umc9th.peter.global.api.ApiResponse;
import com.umc9th.peter.global.api.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping()
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResponse.joinDto> signUp(
            @RequestBody @Valid MemberRequest.joinDto dto
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.CREATED,
                memberService.signUp(dto)
        );
    }

    @PostMapping("/login")
    public ApiResponse<MemberResponse.loginDto> login(
            @RequestBody @Valid MemberRequest.loginDto dto
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.OK,
                memberService.login(dto)
        );
    }

    @DeleteMapping("/members/{memberId}")
    public ApiResponse<Void> delete(
            @PathVariable("memberId") Long memberId
    ) {

        memberService.deleteAccountByMemberId(memberId);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.NO_CONTENT,
                null
        );
    }

    @GetMapping("/members/missions")
    public ResponseEntity<ApiResponse<MemberResponse.MissionListDto>> getMemberMissions(
            @RequestParam(required = false) MissionStatus status,
            @RequestParam(defaultValue = "1") @PageNumber Integer page,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        MissionSuccessCode code = MissionSuccessCode.OK;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onSuccess(
                        code,
                        memberService.getMissions(1L, status, page, limit)
                ));
    }

}
