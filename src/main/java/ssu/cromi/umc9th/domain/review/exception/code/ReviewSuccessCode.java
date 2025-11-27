package ssu.cromi.umc9th.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ssu.cromi.umc9th.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    SUCCESS_REVIEWED(HttpStatus.CREATED,
            "REVIEW201_1",
            "성공적으로 리뷰를 생성했습니다."),
    FOUND(HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 리뷰를 찾았습니다.")
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
