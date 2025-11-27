package com.umc9th.peter.domain.mission.exception.code;

import com.umc9th.peter.global.api.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    BAD_MISSION(HttpStatus.BAD_REQUEST, "MISSION400_1", "올바르지 않은 미션입니다."),
    CLOSED(HttpStatus.BAD_REQUEST, "MISSION400_2", "종료된 미션입니다."),
    NOT_OPENED(HttpStatus.BAD_REQUEST, "MISSION400_3", "예정된 미션입니다."),
    ALREADY_ACCEPTED(HttpStatus.BAD_REQUEST, "MISSION400_4", "이미 수락된 미션입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "해당 미션을 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
