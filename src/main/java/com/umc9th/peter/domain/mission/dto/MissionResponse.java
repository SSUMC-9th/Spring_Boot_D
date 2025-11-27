package com.umc9th.peter.domain.mission.dto;

import com.umc9th.peter.domain.member.entity.mapping.MemberMission;
import com.umc9th.peter.domain.mission.entity.Mission;
import com.umc9th.peter.domain.store.dto.StoreResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponse {

    public record MissionDto(
            Long id,
            StoreResponse.StoreDto store,
            String content,
            LocalDateTime beginAt,
            LocalDateTime endAt
    ) {

        public static MissionDto fromEntity(Mission mission) {
            return new MissionDto(
                    mission.getId(),
                    StoreResponse.StoreDto.fromEntity(mission.getStore()),
                    mission.getContent(),
                    mission.getBeginAt(),
                    mission.getEndAt()
            );
        }

    }

    @Builder
    public record MissionListDto(
            List<MissionDto> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

    public record AcceptDto(
            Long id,
            LocalDateTime createdAt
    ) {

        public static MissionResponse.AcceptDto fromEntity(MemberMission memberMission) {
            return new MissionResponse.AcceptDto(
                    memberMission.getId(),
                    memberMission.getCreatedAt()
            );
        }

    }

}
