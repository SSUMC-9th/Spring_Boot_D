package com.umc9th.peter.domain.mission.repository;

import com.umc9th.peter.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT DISTINCT m FROM Mission m " +
            "JOIN FETCH m.missionDistrictList md " +
            "WHERE md.district.id  = :districtId " +
            "AND :now BETWEEN m.beginAt AND m.endAt")
    Page<Mission> findAvailableMissionsByDistrictId(Long districtId, LocalDateTime now, Pageable pageable);

}
