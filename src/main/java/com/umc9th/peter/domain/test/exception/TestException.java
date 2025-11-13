package com.umc9th.peter.domain.test.exception;

import com.umc9th.peter.global.api.code.BaseErrorCode;
import com.umc9th.peter.global.api.exception.GeneralException;

public class TestException extends GeneralException {

    public TestException(BaseErrorCode code) {
        super(code);
    }

}
