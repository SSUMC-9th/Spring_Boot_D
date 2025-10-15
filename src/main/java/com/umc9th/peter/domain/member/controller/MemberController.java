package com.umc9th.peter.domain.member.controller;

import com.umc9th.peter.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> delete(@PathVariable("memberId") Long memberId) {
        memberService.deleteAccountByMemberId(memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
