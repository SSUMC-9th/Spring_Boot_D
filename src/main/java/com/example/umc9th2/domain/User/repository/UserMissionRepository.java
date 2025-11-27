package com.example.umc9th2.domain.User.repository;

import com.example.umc9th2.domain.User.entity.mapping.UserMission;
import com.example.umc9th2.domain.mission.dto.UserMissionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.umc9th2.domain.User.entity.User;
import com.example.umc9th2.domain.mission.entity.Mission;


@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    // 마이페이지 화면 : 성공한 미션 포인트 합계
    // 1. 미션 하나도 없으면 0점 처리
    // 2. mission 테이블과 조인하여 point 조회 가능
    // 3. 로그인한 사용기준 필터링
    // 4. 성공한 미션만 집계
    @Query("""
        SELECT COALESCE(SUM(m.point), 0)
        FROM UserMission um
        JOIN um.mission m
        WHERE um.user.userId = :userId
          AND um.isComplete = true
    """)
    Long sumSuccessPointsByUserId(@Param("userId") Long userId);

    // 내가 진행 중 / 완료한 미션 목록 조회 (페이징)
    // 1. 필요한 데이터만 UsermissionDto에 담기
    // 2. mission, store 테이블과 조인하여 정보 가져오기
    // 3. 로그인한 사용자 기준 조회
    // 4. 최근 생성된 미션부터 정렬
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

    boolean existsByUserAndMission(User user, Mission mission);

    Page<UserMission> findAllByUser_UserId(Long userId, Pageable pageable);


}
