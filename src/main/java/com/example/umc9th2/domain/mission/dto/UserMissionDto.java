package com.example.umc9th2.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserMissionDto {
    private Long userMissionId;
    private Long missionId;
    private String storeName;
    private String missionDescription;
    private LocalDate missionDeadline;
    private Integer point;
    private Boolean isSuccess;
    private LocalDateTime createdAt;
}

