package ssu.cromi.umc9th.domain.mission.exception.MissionException;

import ssu.cromi.umc9th.global.apiPayload.code.BaseErrorCode;
import ssu.cromi.umc9th.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException{
    public MissionException(BaseErrorCode code) {super(code);}
}
