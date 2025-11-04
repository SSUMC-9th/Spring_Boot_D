package com.umc9th.peter.domain.member.repository;

import com.umc9th.peter.domain.member.entity.mapping.MemberTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTermRepository extends JpaRepository<MemberTerm, Long> {

    @Modifying
    @Query("DELETE FROM MemberTerm mt WHERE mt.member.id = :memberId")
    void deleteByMemberId(@Param("memberId") Long memberId);

}
