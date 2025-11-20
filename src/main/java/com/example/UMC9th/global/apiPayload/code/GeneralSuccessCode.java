package com.example.UMC9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    //공통
    OK(HttpStatus.OK,
            "COMMON200",
            "성공적으로 요청을 처리했습니다."),

    CREATED(HttpStatus.CREATED,
            "COMMON201_1",
            "성공적으로 리소스가 생성되었습니다."),


    // 리뷰
    REVIEW_CREATE_SUCCESS(HttpStatus.CREATED,
            "REVIEW201_1",
                    "리뷰가 성공적으로 등록되었습니다."),

    REVIEW_READ_SUCCESS(HttpStatus.OK,
            "REVIEW200_1",
                    "리뷰 조회 성공"),

    REVIEW_SEARCH_SUCCESS(HttpStatus.OK,
            "REVIEW200_2",
                    "리뷰 검색 성공");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
