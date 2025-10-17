package ssu.cromi.umc9th.domain.mission.dto;

import lombok.*;
import ssu.cromi.umc9th.domain.mission.enums.UserMissionStatus;

@Getter
@AllArgsConstructor
public class UserMissionDto {
    private Long point;
    private UserMissionStatus status;
    private String storeName;
    private String content;
}