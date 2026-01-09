package com.umc9th.peter.domain.member.repository;

import com.umc9th.peter.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    // 홈 화면 (상단부) 쿼리
    @Query("SELECT m FROM Member m " +
            "LEFT JOIN FETCH m.memberDistrictList md " +
            "LEFT JOIN FETCH md.district " +
            "WHERE m.id = :memberId")
    Optional<Member> findByIdWithMemberDistrictList(@Param("memberId") Long memberId);

    // 마이페이지 화면 쿼리
    @Query("SELECT DISTINCT mem FROM Member mem " +
            "LEFT JOIN FETCH mem.memberMissionList mm " +
            "LEFT JOIN FETCH mm.mission mis " +
            "LEFT JOIN FETCH mis.store " +
            "WHERE mem.id = :memberId")
    Optional<Member> findByIdWithMemberMissionList(@Param("memberId") Long memberId);

}
