package com.umc9th.peter.domain.review.exception;

import com.umc9th.peter.global.api.code.BaseErrorCode;
import com.umc9th.peter.global.api.exception.GeneralException;

public class ReviewException extends GeneralException {

    public ReviewException(BaseErrorCode code) {
        super(code);
    }

}
