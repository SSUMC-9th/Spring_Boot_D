package com.umc9th.peter.domain.member.repository;

import com.umc9th.peter.domain.member.entity.mapping.MemberFoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberFoodCategoryRepository extends JpaRepository<MemberFoodCategory, Long> {

    @Modifying
    @Query("DELETE FROM MemberFoodCategory mfc WHERE mfc.member.id = :memberId")
    void deleteByMemberId(@Param("memberId") long memberId);

}
