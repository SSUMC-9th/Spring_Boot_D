package com.example.umc9th2.domain.store.exception.code;

import com.example.umc9th2.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum StoreSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK, "STORE200_1", "가게 조회 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    StoreSuccessCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
