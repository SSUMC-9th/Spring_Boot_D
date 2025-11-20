package com.example.umc9th2.domain.Food.exception;

import com.example.umc9th2.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class FoodException extends RuntimeException {

  private final BaseErrorCode errorCode;

  public FoodException(BaseErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
