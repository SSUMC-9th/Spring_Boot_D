package com.umc9th.peter.domain.member.repository;

import com.umc9th.peter.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT DISTINCT mem FROM Member mem " +
            "LEFT JOIN FETCH mem.memberMissionList mm " +
            "LEFT JOIN FETCH mm.mission mis " +
            "LEFT JOIN FETCH mis.store " +
            "WHERE mem.id = :memberId")
    Member findByIdWithMemberMissionList(@Param("memberId") Long memberId);

}
