package com.umc9th.peter.domain.member.exception.code;

import com.umc9th.peter.global.api.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD404_1", "올바르지 않은 id입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
