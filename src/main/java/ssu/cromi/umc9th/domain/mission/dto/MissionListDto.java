package ssu.cromi.umc9th.domain.mission.dto;
import lombok.*;

@Getter
@AllArgsConstructor
public class MissionListDto {
    private Long missionId;
    private String storeName;
    private String address;
    private String content;
    private Long point;
    private String missionStatus;
}