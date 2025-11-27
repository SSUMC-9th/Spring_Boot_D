package com.umc9th.peter.domain.member.exception;

import com.umc9th.peter.global.api.code.BaseErrorCode;
import com.umc9th.peter.global.api.exception.GeneralException;

public class FoodException extends GeneralException {

    public FoodException(BaseErrorCode code) {
        super(code);
    }

}
