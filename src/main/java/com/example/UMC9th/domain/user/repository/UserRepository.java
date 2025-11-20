package com.example.UMC9th.domain.user.repository;


import com.example.UMC9th.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.missionList WHERE u.userId = :userId")
    User finduserIdWithMissionList(@Param("userId") Long usesrId);
}
