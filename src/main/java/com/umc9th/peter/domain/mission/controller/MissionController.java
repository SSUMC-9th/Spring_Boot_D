package com.umc9th.peter.domain.mission.controller;

import com.umc9th.peter.domain.mission.dto.MissionResponse;
import com.umc9th.peter.domain.mission.exception.code.MissionSuccessCode;
import com.umc9th.peter.domain.mission.service.MissionService;
import com.umc9th.peter.global.annotation.PageNumber;
import com.umc9th.peter.global.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ResponseEntity<ApiResponse<MissionResponse.MissionListDto>> getMissions(
            @RequestParam Long districtId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(defaultValue = "1") @PageNumber Integer page,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        MissionSuccessCode code = MissionSuccessCode.OK;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onSuccess(
                        code,
                        missionService.getMissions(districtId, storeId, page, limit)
                ));
    }

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
