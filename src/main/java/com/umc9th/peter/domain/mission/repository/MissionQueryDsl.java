package com.umc9th.peter.domain.mission.repository;

import com.umc9th.peter.domain.mission.dto.MissionRequest;
import com.umc9th.peter.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionQueryDsl {

    Page<Mission> searchMissionsByConditions(
            MissionRequest.SearchConditionDto conditions,
            Pageable pageable
    );

}
