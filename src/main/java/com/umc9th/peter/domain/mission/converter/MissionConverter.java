package com.umc9th.peter.domain.mission.converter;

import com.umc9th.peter.domain.mission.dto.MissionResponse;
import com.umc9th.peter.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResponse.MissionListDto toMissionListDto(
            Page<Mission> result
    ) {
        List<MissionResponse.MissionDto> missionDtos = result.stream()
                .map(MissionResponse.MissionDto::fromEntity)
                .toList();

        return MissionResponse.MissionListDto.builder()
                .missionList(missionDtos)
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

}
