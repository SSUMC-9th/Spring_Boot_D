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
    // TODO: 추후 커서 기반 페이징으로 개선 고려
    // 현재는 Page 기반 페이징을 사용하지만, 대용량 데이터 처리 시 성능 개선을 위해
    // 커서 기반 페이징(Cursor-based Pagination) 도입을 검토할 수 있습니다.
    @Query("""
        SELECT new ssu.cromi.umc9th.domain.mission.dto.UserMissionDto(
            m.point, um.status, s.storeName, m.content
        )
        FROM UserMission um
        JOIN um.mission m
        JOIN m.store s
        WHERE um.user.id = :userId
        AND um.status = :status
        ORDER BY um.createdAt DESC
        """)
    Page<UserMissionDto> findUserMissions(
            @Param("userId") Long userId,
            @Param("status") UserMissionStatus status,
            Pageable pageable);
}
