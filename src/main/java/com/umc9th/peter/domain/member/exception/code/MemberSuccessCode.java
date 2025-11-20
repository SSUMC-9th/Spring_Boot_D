package com.umc9th.peter.domain.member.exception.code;

import com.umc9th.peter.global.api.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "MEMBER200_1", "요청이 완료되었습니다."),
    CREATED(HttpStatus.CREATED, "MEMBER201_1", "회원 가입이 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
