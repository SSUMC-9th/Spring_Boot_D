package com.umc9th.peter.domain.mission.repository;

import com.umc9th.peter.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long>, MissionQueryDsl {

    @Query("SELECT DISTINCT m FROM Mission m " +
            "JOIN m.missionDistrictList md " +
            "WHERE md.district.id  = :districtId " +
            "AND :now BETWEEN m.beginAt AND m.endAt " +
            "AND m.id NOT IN (" +
            "   SELECT mm.mission.id " +
            "   FROM MemberMission mm " +
            "   WHERE mm.member.id = :memberId" +
            ")")
    Page<Mission> findAvailableMissionsByDistrictId(@Param("memberId") Long memberId, @Param("districtId") Long districtId, @Param("now") LocalDateTime now, Pageable pageable);

}
