package ssu.cromi.umc9th.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ssu.cromi.umc9th.domain.mission.dto.UserMissionDto;
import ssu.cromi.umc9th.domain.mission.entity.UserMission;
import ssu.cromi.umc9th.domain.mission.enums.UserMissionStatus;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    @Query("SELECT new ssu.cromi.umc9th.domain.mission.dto.UserMissionDto(" +"m.point, um.status, s.storeName, m.content) " +
            "FROM UserMission um " +
            "JOIN um.mission m " +
            "JOIN m.store s " +
            "WHERE um.user.id = :userId " +
            "AND um.status = :status " +
            "ORDER BY um.createdAt DESC")
    Page<UserMissionDto> findUserMissions(@Param("userId") Long userId, @Param("status") UserMissionStatus status, Pageable pageable);
}
