package com.example.umc9th2.domain.mission.controller;

import com.example.umc9th2.domain.mission.service.command.MissionCommandService;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionCommandController {

    private final MissionCommandService missionCommandService;
    //미션 도전
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<Void> challengeMission(@PathVariable Long missionId) {

        missionCommandService.challengeMission(missionId);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.MISSION_CHALLENGE_SUCCESS,
                null
        );
    }
}

