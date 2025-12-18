package com.example.umc9th2.domain.mission.controller;

import com.example.umc9th2.domain.mission.dto.MissionResponseDto;
import com.example.umc9th2.global.annotation.ValidPage;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Mission", description = "가게 미션 조회 API")
public interface MissionControllerDocs {

    @Operation(
            summary = "특정 가게 미션 목록 조회",
            description = "가게 ID 기반 미션 페이징 조회 API"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "가게 없음")
    })
    ApiResponse<MissionResponseDto.MissionListDTO> getStoreMissions(
            @RequestParam Long storeId,
            @ValidPage @RequestParam Integer page
    );


    @Operation(
            summary = "내가 진행 중인 미션 목록 조회",
            description = "userId 기반으로 내가 도전 중인 미션을 10개 단위로 페이징 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    ApiResponse<MissionResponseDto.MissionListDTO> getMyMissionList(
            @RequestParam Long userId,
            @ValidPage @RequestParam Integer page
    );


    @Operation(
            summary = "미션 도전하기",
            description = "missionId를 기반으로 특정 미션에 도전합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "미션 도전 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "미션 없음")
    })
    ApiResponse<Void> challengeMission(
            @RequestParam Long missionId
    );
}
