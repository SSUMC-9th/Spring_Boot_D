package ssu.cromi.umc9th.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ssu.cromi.umc9th.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "해당 리뷰를 찾지 못했습니다."
    ),
    INVALID_SCORE(HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "별점은 0.0에서 5.0 사이의 값이어야 합니다."
    ),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
