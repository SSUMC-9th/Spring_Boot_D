package com.example.umc9th2.global.common.response;

import com.example.umc9th2.global.code.SuccessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {

    private final boolean isSuccess;
    private final String code;
    private final String message;
    private final T result;

    public static <T> BaseResponse<T> onSuccess(SuccessStatus status, T result) {
        return new BaseResponse<>(true, status.getCode(), status.getMessage(), result);
    }

    public static <T> BaseResponse<T> onFailure(String code, String message) {
        return new BaseResponse<>(false, code, message, null);
    }
}
