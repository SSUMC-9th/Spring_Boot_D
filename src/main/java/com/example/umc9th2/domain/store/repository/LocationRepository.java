package com.example.umc9th2.domain.store.repository;

import com.example.umc9th2.domain.store.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
