package com.example.UMC9th.domain.mission.service.query;

import com.example.UMC9th.domain.mission.dto.MissionResDTO;

public interface MissionQueryService {

    MissionResDTO.StoreMissionPreviewListDTO getStoreMissions(
            Long storeId,
            Integer page
    );

    MissionResDTO.MyMissionPreviewListDTO getMyMissions(
            Long userId,
            Integer page
    );
}
