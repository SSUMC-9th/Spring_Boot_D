package com.umc9th.peter.domain.store.repository;

import com.umc9th.peter.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Modifying
    @Query("UPDATE Store s SET s.owner = null WHERE s.owner.id = :ownerId")
    void clearOwnerByOwnerId(@Param("ownerId") long ownerId);

}
