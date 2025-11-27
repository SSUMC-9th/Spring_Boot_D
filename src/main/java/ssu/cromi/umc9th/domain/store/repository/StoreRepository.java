package ssu.cromi.umc9th.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssu.cromi.umc9th.domain.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
