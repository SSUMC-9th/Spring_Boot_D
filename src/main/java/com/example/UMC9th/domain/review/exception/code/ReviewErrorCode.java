package com.example.UMC9th.domain.review.exception.code;

import com.example.UMC9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "해당 사용자를 찾지 못했습니다."),
    ;

    private HttpStatus status;
    private String code;
    private String message;
}
