package com.umc9th.peter.domain.mission.exception.code;

import com.umc9th.peter.global.api.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "MISSION200_1", "요청이 완료되었습니다."),
    MISSION_ACCEPTED(HttpStatus.OK, "MISSION200_2", "미션이 수락되었습니다."),
    CREATED(HttpStatus.CREATED, "MISSION201_1", "미션이 등록되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
