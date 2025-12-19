package com.example.umc9th2.domain.mission.controller;

import com.example.umc9th2.domain.mission.dto.MissionResponseDto;
import com.example.umc9th2.domain.mission.service.command.MissionCommandService;
import com.example.umc9th2.domain.mission.service.query.MissionQueryService;
import com.example.umc9th2.global.annotation.ValidPage;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
@Tag(name = "Mission", description = "미션 관련 API")
public class MissionController implements MissionControllerDocs {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    //특정 가게 미션 목록 조회
    //api/missions에 특정 가게 미션 목록 조회가 맞는 url인지 다시 생각해보기 직관적이지 않은듯
    @GetMapping
    @Override
    public ApiResponse<MissionResponseDto.MissionListDTO> getStoreMissions(
            @RequestParam Long storeId,
            @ValidPage @RequestParam Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getStoreMissions(storeId, page)
        );
    }

    //내가 진행 중인 미션 목록 조회
    @GetMapping("/my")
    @Override
    public ApiResponse<MissionResponseDto.MissionListDTO> getMyMissionList(
            @RequestParam Long userId,//어떤 사용자인지
            @ValidPage @RequestParam Integer page//페이지 번호
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getMyMissions(userId, page)
        );
    }

    //미션 도전하기
    @PostMapping("/{missionId}/challenge")
    @Override
    public ApiResponse<Void> challengeMission(
            @PathVariable Long missionId
    ) {
        missionCommandService.challengeMission(missionId);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.MISSION_CHALLENGE_SUCCESS,
                null
        );
    }
}
