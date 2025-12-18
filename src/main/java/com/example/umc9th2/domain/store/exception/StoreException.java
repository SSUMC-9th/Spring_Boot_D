package com.example.umc9th2.domain.store.exception;

import com.example.umc9th2.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th2.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}