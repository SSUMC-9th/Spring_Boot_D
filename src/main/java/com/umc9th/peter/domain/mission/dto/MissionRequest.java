package com.umc9th.peter.domain.mission.dto;

public class MissionRequest {

    public record SearchConditionDto(
            Long districtId,
            Long storeId
    ) {
    }

}
