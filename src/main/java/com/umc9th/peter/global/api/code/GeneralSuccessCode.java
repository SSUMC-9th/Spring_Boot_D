package com.umc9th.peter.global.api.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "COMMON200_1", "요청이 처리되었습니다."),
    NO_CONTENT(HttpStatus.NO_CONTENT, "COMMON204_1", "요청이 처리되었습니다."),
    FOUND(HttpStatus.FOUND, "REDIRECT302_1", "URI가 일시적으로 변경되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
