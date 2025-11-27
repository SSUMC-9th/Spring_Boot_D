package com.example.UMC9th.domain.mission.dto;

import com.example.UMC9th.domain.mission.enums.MissonState;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {
    Long missionId;
    String missionContent;
    Integer point;

    // 가게 미션 목록

    @Builder
    public record StoreMissionPreviewDTO(
            Long missionId,
            String content,
            Integer point,
            LocalDate deadline
    ) {}

    @Builder
    public record StoreMissionPreviewListDTO(
            List<StoreMissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 내가 진행중인 미션 목록

    @Builder
    public record MyMissionPreviewDTO(
            Long missionId,
            String storeName,
            String content,
            Integer point,
            LocalDate deadline,
            MissonState status   // 진행중 / 완료 등
    ) {}

    @Builder
    public record MyMissionPreviewListDTO(
            List<MyMissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}
