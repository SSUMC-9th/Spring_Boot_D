package ssu.cromi.umc9th.domain.store.exception.StoreException;

import ssu.cromi.umc9th.global.apiPayload.code.BaseErrorCode;
import ssu.cromi.umc9th.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
