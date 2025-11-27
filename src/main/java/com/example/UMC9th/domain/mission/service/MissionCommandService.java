package com.example.UMC9th.domain.mission.service;

import com.example.UMC9th.domain.mission.dto.MissionReqDTO;
import com.example.UMC9th.domain.mission.dto.MissionResDTO;

public interface MissionCommandService {

    // 미션 도전 시작
    MissionResDTO.ChallengeDTO startChallenge(
            Long missionId,
            Long userId,
            MissionReqDTO.ChallengeDTO dto
    );
}