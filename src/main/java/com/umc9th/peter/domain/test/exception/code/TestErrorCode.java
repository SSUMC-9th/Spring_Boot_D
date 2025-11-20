package com.umc9th.peter.domain.test.exception.code;

import com.umc9th.peter.global.api.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TestErrorCode implements BaseErrorCode {

    TEST_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST400_1", "테스트 에러 메세지"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
