package com.example.umc9th2.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 전역 및 도메인별 성공 코드 관리 Enum
 */
@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "COMMON200_1",
            "요청이 성공적으로 처리되었습니다."),

    CREATED(HttpStatus.CREATED,
            "COMMON201_1",
            "리소스가 성공적으로 생성되었습니다."),

    //미션
    MISSION_CHALLENGE_SUCCESS(HttpStatus.CREATED, "MISSION_201", "미션 도전이 등록되었습니다."),
    //회원가입 로그인
    SIGNUP_SUCCESS(HttpStatus.CREATED,
            "USER201_1",
            "회원가입이 성공적으로 완료되었습니다."),

    LOGIN_SUCCESS(HttpStatus.OK,
            "USER200_1",
            "로그인이 성공적으로 완료되었습니다."),

    LOGOUT_SUCCESS(HttpStatus.OK,
            "USER200_2",
            "로그아웃이 성공적으로 완료되었습니다."),

    PASSWORD_CHANGE_SUCCESS(HttpStatus.OK,
            "USER200_3",
            "비밀번호가 성공적으로 변경되었습니다."),

    PROFILE_UPDATE_SUCCESS(HttpStatus.OK,
            "USER200_4",
            "회원 정보가 성공적으로 수정되었습니다."),

    // 리뷰
    REVIEW_CREATE_SUCCESS(HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰가 성공적으로 등록되었습니다."),

    REVIEW_READ_SUCCESS(HttpStatus.OK,
            "REVIEW200_1",
            "리뷰 조회 성공"),

    REVIEW_UPDATE_SUCCESS(HttpStatus.OK,
            "REVIEW200_2",
            "리뷰 수정 성공"),

    REVIEW_DELETE_SUCCESS(HttpStatus.OK,
            "REVIEW200_3",
            "리뷰 삭제 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
