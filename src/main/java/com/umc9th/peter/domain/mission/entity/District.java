package com.umc9th.peter.domain.mission.entity;

import com.umc9th.peter.domain.mission.entity.mapping.MissionDistrict;
import com.umc9th.peter.domain.mission.enums.DistrictStatus;
import com.umc9th.peter.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "district")
public class District extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private DistrictStatus status = DistrictStatus.ACTIVE;

    @OneToMany(mappedBy = "district")
    @Builder.Default
    private List<MissionDistrict> districtMissionList = new ArrayList<>();

}
