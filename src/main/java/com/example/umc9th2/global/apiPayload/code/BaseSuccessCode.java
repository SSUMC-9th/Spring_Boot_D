package com.example.umc9th2.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {

    HttpStatus getStatus();   // HTTP 상태 코드 (ex. 200, 201)
    String getCode();         // 비즈니스 성공 코드 (ex. COMMON200_1)
    String getMessage();      // 사용자에게 전달할 메시지
}
