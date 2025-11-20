package com.umc9th.peter.domain.member.controller;

import com.umc9th.peter.domain.member.dto.MemberRequest;
import com.umc9th.peter.domain.member.dto.MemberResponse;
import com.umc9th.peter.domain.member.exception.code.MemberSuccessCode;
import com.umc9th.peter.domain.member.service.MemberService;
import com.umc9th.peter.global.api.ApiResponse;
import com.umc9th.peter.global.api.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResponse.joinDto> signUp(
            @RequestBody MemberRequest.joinDto dto
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.CREATED,
                memberService.signUp(dto)
        );
    }

    @DeleteMapping("/{memberId}")
    public ApiResponse<Void> delete(
            @PathVariable("memberId") Long memberId
    ) {

        memberService.deleteAccountByMemberId(memberId);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.NO_CONTENT,
                null
        );
    }

}
