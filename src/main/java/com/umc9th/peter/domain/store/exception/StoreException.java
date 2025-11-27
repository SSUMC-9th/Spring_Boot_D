package com.umc9th.peter.domain.store.exception;

import com.umc9th.peter.global.api.code.BaseErrorCode;
import com.umc9th.peter.global.api.exception.GeneralException;

public class StoreException extends GeneralException {

    public StoreException(BaseErrorCode code) {
        super(code);
    }

}
