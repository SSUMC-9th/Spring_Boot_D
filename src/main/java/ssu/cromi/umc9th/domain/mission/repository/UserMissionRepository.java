package ssu.cromi.umc9th.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ssu.cromi.umc9th.domain.mission.dto.UserMissionDto;
import ssu.cromi.umc9th.domain.mission.entity.UserMission;
import ssu.cromi.umc9th.domain.mission.enums.UserMissionStatus;
import ssu.cromi.umc9th.domain.user.entity.User;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    @Query("""
        SELECT new ssu.cromi.umc9th.domain.mission.dto.UserMissionDto(
            m.point, um.status, s.storeName, m.content
        )
        FROM UserMission um
        JOIN um.mission m
        JOIN m.store s
        WHERE um.user.userId = :userId
        AND um.status = :status
        ORDER BY um.createdAt DESC
        """)
    Page<UserMissionDto> findUserMissions(
            @Param("userId") Long userId,
            @Param("status") UserMissionStatus status,
            Pageable pageable);

    // 사용자가 특정 미션에 이미 도전 중인지 확인
    @Query("""
        SELECT COUNT(um) > 0
        FROM UserMission um
        WHERE um.user.userId = :userId
        AND um.mission.id = :missionId
        AND um.status IN ('ASSIGNED', 'IN_PROGRESS')
        """)
    boolean existsActiveUserMission(
            @Param("userId") Long userId,
            @Param("missionId") Long missionId);

    Page<UserMission> findAllByUserAndStatus(User user, UserMissionStatus status, PageRequest pageRequest);
}
