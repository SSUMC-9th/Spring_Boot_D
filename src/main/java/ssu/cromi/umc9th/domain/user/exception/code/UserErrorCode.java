package ssu.cromi.umc9th.domain.user.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ssu.cromi.umc9th.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾지 못했습니다."
            ),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
