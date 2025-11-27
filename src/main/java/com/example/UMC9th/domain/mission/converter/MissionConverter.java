package com.example.UMC9th.domain.mission.converter;


import com.example.UMC9th.domain.mission.dto.MissionReqDTO;
import com.example.UMC9th.domain.mission.dto.MissionResDTO;
import com.example.UMC9th.domain.mission.entity.Mission;
import com.example.UMC9th.domain.mission.enums.MissonState;
import com.example.UMC9th.domain.user.entity.User;
import com.example.UMC9th.domain.store.entity.Store;
import com.example.UMC9th.domain.user.entity.mapping.UserMission;
import org.springframework.data.domain.Page;


public class MissionConverter {

    // 가게 미션 목록용
    public static MissionResDTO.StoreMissionPreviewListDTO toStoreMissionPreviewListDTO(
            Page<Mission> result
    ) {
        return MissionResDTO.StoreMissionPreviewListDTO.builder()
                .missionList(
                        result.getContent().stream()
                                .map(MissionConverter::toStoreMissionPreviewDTO)
                                .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.StoreMissionPreviewDTO toStoreMissionPreviewDTO(
            Mission mission
    ) {
        return MissionResDTO.StoreMissionPreviewDTO.builder()
                .missionId(mission.getMissionId())
                .content(mission.getMissionContent())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    // 내가 진행중인 미션 목록용
    public static MissionResDTO.MyMissionPreviewListDTO toMyMissionPreviewListDTO(
            Page<UserMission> result
    ) {
        return MissionResDTO.MyMissionPreviewListDTO.builder()
                .missionList(
                        result.getContent().stream()
                                .map(MissionConverter::toMyMissionPreviewDTO)
                                .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MyMissionPreviewDTO toMyMissionPreviewDTO(
            UserMission userMission
    ) {
        Mission mission = userMission.getMission();
        Store store = mission.getStore();

        return MissionResDTO.MyMissionPreviewDTO.builder()
                .missionId(mission.getMissionId())
                .storeName(store.getStoreName())
                .content(mission.getMissionContent())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .status(userMission.getMissionState())
                .build();
    }
}

