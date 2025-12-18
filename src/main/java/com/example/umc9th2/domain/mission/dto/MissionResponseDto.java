package com.example.umc9th2.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionResponseDto {

    private Long missionId;
    private String title;
    private Integer point;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MissionListDTO {
        private List<MissionResponseDto> missions;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}

