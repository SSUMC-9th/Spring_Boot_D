package com.example.UMC9th.domain.review.exception;
import com.example.UMC9th.global.apiPayload.code.BaseErrorCode;
import com.example.UMC9th.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
