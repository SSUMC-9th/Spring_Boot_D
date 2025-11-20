package com.umc9th.peter.domain.store.exception.code;

import com.umc9th.peter.global.api.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "STORE200_1", "요청이 완료되었습니다."),
    CREATED(HttpStatus.CREATED, "STORE201_1", "식당 등록이 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
