package com.example.UMC9th.domain.test.exception;

import com.example.UMC9th.global.apiPayload.code.BaseErrorCode;
import com.example.UMC9th.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code)
    {
        super(code);
    }
}
