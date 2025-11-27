package ssu.cromi.umc9th.domain.food.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ssu.cromi.umc9th.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum FoodSuccessCode implements BaseSuccessCode{
    FOUND(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 사용자를 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
