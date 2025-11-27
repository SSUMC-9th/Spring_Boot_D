package com.example.UMC9th.domain.mission.exception.code;

import com.example.UMC9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    GET_MISSION(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 불러왔습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
