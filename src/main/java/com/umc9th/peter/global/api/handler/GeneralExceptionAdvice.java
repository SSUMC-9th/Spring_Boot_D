package com.umc9th.peter.global.api.handler;

import com.umc9th.peter.global.api.ApiResponse;
import com.umc9th.peter.global.api.code.BaseErrorCode;
import com.umc9th.peter.global.api.code.GeneralErrorCode;
import com.umc9th.peter.global.api.exception.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<String>> handleException(GeneralException e) {
        BaseErrorCode code = e.getCode();
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, code.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception e) {
        GeneralErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, e.getMessage()));
    }

}
