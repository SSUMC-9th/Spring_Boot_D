package com.example.umc9th2.domain.mission.converter;

import com.example.umc9th2.domain.mission.dto.MissionResponseDto;
import com.example.umc9th2.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

public class MissionConverter {

    public static MissionResponseDto toDto(Mission mission) {
        return MissionResponseDto.builder()
                .missionId(mission.getMissionId())
                .title(mission.getContent())   // ★ title → content 로 변경
                .point(mission.getPoint())
                .build();
    }

    public static MissionResponseDto.MissionListDTO toListDTO(Page<Mission> result) {
        return MissionResponseDto.MissionListDTO.builder()
                .missions(
                        result.getContent().stream()
                                .map(MissionConverter::toDto)
                                .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }
}

