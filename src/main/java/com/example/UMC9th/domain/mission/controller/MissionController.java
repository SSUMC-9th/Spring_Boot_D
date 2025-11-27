package com.example.UMC9th.domain.mission.controller;

import com.example.UMC9th.domain.mission.dto.MissionReqDTO;
import com.example.UMC9th.domain.mission.dto.MissionResDTO;
import com.example.UMC9th.domain.mission.service.MissionCommandService;
import com.example.UMC9th.domain.mission.service.query.MissionQueryService;
import com.example.UMC9th.global.apiPayload.ApiResponse;
import com.example.UMC9th.domain.mission.exception.code.MissionSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionQueryService missionQueryService;

    // 1) 특정 가게의 미션 목록
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.StoreMissionPreviewListDTO> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        MissionSuccessCode code = MissionSuccessCode.GET_MISSION; // enum에 하나 추가해도 좋고
        return ApiResponse.onSuccess(
                code,
                missionQueryService.getStoreMissions(storeId, page)
        );
    }

    // 2) 내가 진행중인 미션 목록
    @GetMapping("/users/{userId}/missions/my")
    public ApiResponse<MissionResDTO.MyMissionPreviewListDTO> getMyMissions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        MissionSuccessCode code = MissionSuccessCode.GET_MISSION; // 필요하면 추가
        return ApiResponse.onSuccess(
                code,
                missionQueryService.getMyMissions(userId, page)
        );
    }

}
