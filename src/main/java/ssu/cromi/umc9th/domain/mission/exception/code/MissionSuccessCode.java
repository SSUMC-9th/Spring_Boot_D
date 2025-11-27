package ssu.cromi.umc9th.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ssu.cromi.umc9th.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    SUCCESS_CHALLENGED(HttpStatus.CREATED,
            "MISSION201_1",
            "성공적으로 미션에 도전했습니다."),
    FOUND(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 찾았습니다.")
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
