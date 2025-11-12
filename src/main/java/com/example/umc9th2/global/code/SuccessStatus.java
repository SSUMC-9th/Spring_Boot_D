package com.example.umc9th2.global.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 성공 상태 코드 정의
 * - Controller에서 응답 시 BaseResponse와 함께 사용
 */
@Getter
@RequiredArgsConstructor
public enum SuccessStatus {

    REVIEW_READ_SUCCESS("REVIEW_200", "리뷰 조회 성공"),
    USER_CREATE_SUCCESS("USER_200", "유저 생성 성공"),
    MISSION_COMPLETE_SUCCESS("MISSION_200", "미션 완료 성공");

    private final String code;
    private final String message;
}

