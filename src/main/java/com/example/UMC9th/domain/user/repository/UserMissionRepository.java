package com.example.UMC9th.domain.user.repository;

import com.example.UMC9th.domain.user.entity.User;
import com.example.UMC9th.domain.user.entity.mapping.UserMission;
import com.example.UMC9th.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Integer> {
}
