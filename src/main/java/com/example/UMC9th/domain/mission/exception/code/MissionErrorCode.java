package com.example.UMC9th.domain.mission.exception.code;

import com.example.UMC9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당 미션를 찾지 못했습니다.")
    ;

    private HttpStatus status;
    private String code;
    private String message;

}
