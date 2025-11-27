package com.example.UMC9th.domain.store.exception.code;

import com.example.UMC9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 리뷰를 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}