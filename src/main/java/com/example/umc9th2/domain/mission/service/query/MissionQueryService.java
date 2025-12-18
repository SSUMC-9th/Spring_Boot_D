package com.example.umc9th2.domain.mission.service.query;

import com.example.umc9th2.domain.mission.dto.MissionResponseDto;

public interface MissionQueryService {
    // 내가 진행 중인 미션 목록 조회
    MissionResponseDto.MissionListDTO getMyMissions(Long userId, Integer page);

    // 특정 가게 미션 목록 조회
    MissionResponseDto.MissionListDTO getStoreMissions(Long storeId, Integer page);

}
