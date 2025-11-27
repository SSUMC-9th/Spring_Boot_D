package com.umc9th.peter.domain.member.exception;

import com.umc9th.peter.global.api.code.BaseErrorCode;
import com.umc9th.peter.global.api.exception.GeneralException;

public class MemberException extends GeneralException {

    public MemberException(BaseErrorCode code) {
        super(code);
    }

}
