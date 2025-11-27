package ssu.cromi.umc9th.domain.food.exception.FoodException;

import ssu.cromi.umc9th.global.apiPayload.code.BaseErrorCode;
import ssu.cromi.umc9th.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code) {
        super(code);
    }
}
