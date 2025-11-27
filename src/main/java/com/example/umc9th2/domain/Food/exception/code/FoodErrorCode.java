package com.example.umc9th2.domain.Food.exception.code;

import com.example.umc9th2.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_404", "해당 음식이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
