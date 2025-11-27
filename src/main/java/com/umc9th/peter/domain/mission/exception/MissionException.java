package com.umc9th.peter.domain.mission.exception;

import com.umc9th.peter.global.api.code.BaseErrorCode;
import com.umc9th.peter.global.api.exception.GeneralException;

public class MissionException extends GeneralException {

    public MissionException(BaseErrorCode code) {
        super(code);
    }

}
