package com.umc9th.peter.domain.member.repository;

import com.umc9th.peter.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT DISTINCT m FROM Member m LEFT JOIN FETCH m.memberMissionList WHERE m.id = :memberId")
    Member findByIdWithMemberMissionList(@Param("memberId") Long memberId);

}
