package com.example.UMC9th.domain.store.exception;
import com.example.UMC9th.domain.store.exception.code.StoreErrorCode;
import com.example.UMC9th.global.apiPayload.code.BaseErrorCode;
import com.example.UMC9th.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {

    public StoreException(StoreErrorCode code) {
        super(code);
    }
}