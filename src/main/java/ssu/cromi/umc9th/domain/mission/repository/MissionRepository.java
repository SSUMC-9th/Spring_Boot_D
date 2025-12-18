package ssu.cromi.umc9th.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ssu.cromi.umc9th.domain.mission.dto.MissionListDto;
import ssu.cromi.umc9th.domain.mission.entity.Mission;
import ssu.cromi.umc9th.domain.store.entity.Store;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("""
        SELECT new ssu.cromi.umc9th.domain.mission.dto.MissionListDto(
            m.id, s.storeName, s.address, m.content, m.point,
            CASE
                WHEN um.id IS NULL THEN 'mission start'
                ELSE STR(um.status)
            END
        )
        FROM Mission m
        JOIN m.store s
        LEFT JOIN m.userMissions um WITH um.user.userId = :userId
        WHERE s.address LIKE :address
        AND (um.id IS NULL OR um.status = 'ASSIGNED')
        ORDER BY m.createdAt DESC
        """)
    Page<MissionListDto> findAvailableMissions(
            @Param("userId") Long userId,
            @Param("address") String address,
            Pageable pageable);

    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);
}