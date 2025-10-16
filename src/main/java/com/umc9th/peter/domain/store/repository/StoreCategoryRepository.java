package com.umc9th.peter.domain.store.repository;

import com.umc9th.peter.domain.store.entity.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Long> {

}
