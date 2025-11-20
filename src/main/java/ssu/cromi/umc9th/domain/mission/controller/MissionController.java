package ssu.cromi.umc9th.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ssu.cromi.umc9th.domain.mission.dto.MissionReqDTO;
import ssu.cromi.umc9th.domain.mission.dto.MissionResDTO;
import ssu.cromi.umc9th.domain.mission.exception.code.MissionSuccessCode;
import ssu.cromi.umc9th.domain.mission.service.MissionService;
import ssu.cromi.umc9th.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    /**
     * 미션 도전하기
     */
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionResDTO.ChallengeDTO> challengeMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.ChallengeDTO request) {
        MissionResDTO.ChallengeDTO response = missionService.challengeMission(missionId, request);

        return ApiResponse.onSuccess(MissionSuccessCode.SUCCESS_CHALLENGED, response);
    }
}
