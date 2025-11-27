package com.example.umc9th2.domain.review.exception.code;

import com.example.umc9th2.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ReviewSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK, "REVIEW200_2", "리뷰 조회 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ReviewSuccessCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
