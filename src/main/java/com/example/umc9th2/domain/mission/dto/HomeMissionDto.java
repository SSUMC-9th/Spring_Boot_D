package com.example.umc9th2.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class HomeMissionDto {
    private Long missionId;
    private String storeName;
    private String address;
    private String content;
    private Integer point;
    private LocalDate deadline;
}

