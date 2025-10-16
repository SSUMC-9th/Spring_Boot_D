package com.umc9th.peter.domain.mission.repository;

import com.umc9th.peter.domain.mission.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

}
