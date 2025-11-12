package com.example.umc9th2.global.common.response;

import com.example.umc9th2.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th2.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseResponse<T> {

    @JsonProperty("isSuccess")
    private final boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private final T result;

    // 성공한 경우 (결과 포함)
    public static <T> BaseResponse<T> onSuccess(BaseSuccessCode successCode, T result) {
        return new BaseResponse<>(
                true,
                successCode.getCode(),
                successCode.getMessage(),
                result
        );
    }

    // 성공한 경우 (결과 없음)
    public static <T> BaseResponse<T> onSuccess(BaseSuccessCode successCode) {
        return new BaseResponse<>(
                true,
                successCode.getCode(),
                successCode.getMessage(),
                null
        );
    }

    // 실패한 경우 (결과 포함)
    public static <T> BaseResponse<T> onFailure(BaseErrorCode errorCode, T result) {
        return new BaseResponse<>(
                false,
                errorCode.getCode(),
                errorCode.getMessage(),
                result
        );
    }

    // 실패한 경우 (결과 없음)
    public static <T> BaseResponse<T> onFailure(BaseErrorCode errorCode) {
        return new BaseResponse<>(
                false,
                errorCode.getCode(),
                errorCode.getMessage(),
                null
        );
    }
}
