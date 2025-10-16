package com.example.umc9th2.domain.User.repository;

import com.example.umc9th2.domain.User.entity.mapping.UserMission;
import com.example.umc9th2.domain.mission.dto.UserMissionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // ✅ 마이페이지 화면 : 성공한 미션 포인트 합계
    @Query("""
        SELECT COALESCE(SUM(m.point), 0)
        FROM UserMission um
        JOIN um.mission m
        WHERE um.user.userId = :userId
          AND um.isComplete = true
    """)
    Long sumSuccessPointsByUserId(@Param("userId") Long userId);

    // ✅ 내가 진행 중 / 완료한 미션 목록 조회 (페이징)
    @Query("""
        SELECT new com.example.umc9th2.domain.mission.dto.UserMissionDto(
            um.memberMissionId,
            m.missionId,
            s.name,
            m.content,
            m.deadline,
            m.point,
            um.isComplete,
            m.createdAt
        )
        FROM UserMission um
        JOIN um.mission m
        JOIN m.store s
        WHERE um.user.userId = :userId
        ORDER BY m.createdAt DESC
    """)
    Page<UserMissionDto> findMissionsByUserId(
            @Param("userId") Long userId,
            Pageable pageable
    );
}
