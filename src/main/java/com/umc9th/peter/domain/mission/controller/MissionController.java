package com.umc9th.peter.domain.mission.controller;

import com.umc9th.peter.domain.mission.dto.MissionResponse;
import com.umc9th.peter.domain.mission.exception.code.MissionSuccessCode;
import com.umc9th.peter.domain.mission.service.MissionService;
import com.umc9th.peter.global.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/{missionId}/accept")
    public ResponseEntity<ApiResponse<MissionResponse.AcceptDto>> acceptMission(
            @PathVariable Long missionId
    ) {
        MissionSuccessCode code = MissionSuccessCode.MISSION_ACCEPTED;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onSuccess(
                        code,
                        // TODO: 인증 기능 구현되면 토큰에서 memberId 파싱해서 사용
                        missionService.acceptMission(1L, missionId)
                ));
    }

}
