package com.example.umc9th2.domain.mission.repository;

import com.example.umc9th2.domain.mission.dto.HomeMissionDto;
import com.example.umc9th2.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    //홈 화면 미션 목록 조회
    // 홈 화면 미션 목록 조회
    @Query("""
        SELECT new com.example.umc9th2.domain.mission.dto.HomeMissionDto(
            m.missionId,
            s.name,
            s.address,
            m.content,
            m.point,
            m.deadline
        )
        FROM Mission m
        JOIN m.store s
        WHERE s.address LIKE CONCAT('%', :region, '%')
          AND m.deadline >= CURRENT_DATE
        ORDER BY m.deadline DESC
    """)
    Page<HomeMissionDto> findAvailableMissionsByRegion(
            @Param("region") String region,
            Pageable pageable
    );

}
