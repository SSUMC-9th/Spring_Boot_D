package ssu.cromi.umc9th.domain.review.exception.ReviewException;

import ssu.cromi.umc9th.global.apiPayload.code.BaseErrorCode;
import ssu.cromi.umc9th.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
