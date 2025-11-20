package com.umc9th.peter.domain.review.exception.code;

import com.umc9th.peter.global.api.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "REVIEW200_1", "요청이 완료되었습니다."),
    CREATED(HttpStatus.CREATED, "REVIEW201_1", "리뷰가 작성되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
