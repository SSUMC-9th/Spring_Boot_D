package com.example.umc9th2.domain.User.exception;

import com.example.umc9th2.domain.User.exception.code.UserErrorCode;

public class UserException extends RuntimeException {

    private final UserErrorCode errorCode;

    public UserException(UserErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public UserErrorCode getErrorCode() {
        return errorCode;
    }
}

