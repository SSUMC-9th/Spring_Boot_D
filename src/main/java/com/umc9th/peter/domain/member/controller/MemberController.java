package com.umc9th.peter.domain.member.controller;

import com.umc9th.peter.domain.member.service.MemberService;
import com.umc9th.peter.global.api.ApiResponse;
import com.umc9th.peter.global.api.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

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
