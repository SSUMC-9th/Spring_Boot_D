package ssu.cromi.umc9th.domain.user.exception.UserException;

import ssu.cromi.umc9th.global.apiPayload.code.BaseErrorCode;
import ssu.cromi.umc9th.global.apiPayload.exception.GeneralException;

public class UserException extends GeneralException {
    public UserException(BaseErrorCode code){
        super(code);
    }
}
