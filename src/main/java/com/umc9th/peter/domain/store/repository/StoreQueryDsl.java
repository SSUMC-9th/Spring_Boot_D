package com.umc9th.peter.domain.store.repository;

import com.umc9th.peter.domain.store.dto.StoreSearchCondition;
import com.umc9th.peter.domain.store.entity.Store;
import org.springframework.data.domain.Page;

public interface StoreQueryDsl {

    Page<Store> searchStoresByConditions(StoreSearchCondition conditions);

}
