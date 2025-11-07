package com.umc9th.peter.domain.store.service;

import com.umc9th.peter.domain.store.dto.StoreResponse;
import com.umc9th.peter.domain.store.dto.StoreSearchCondition;
import com.umc9th.peter.domain.store.entity.Store;
import com.umc9th.peter.domain.store.enums.StoreSearchOrder;
import com.umc9th.peter.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public List<StoreResponse> getStores(Long districtId, String keywords, StoreSearchOrder order, Pageable pageable) {
        List<String> nameList = Arrays.stream(Optional.ofNullable(keywords).orElse("").trim().split("\\s+")).toList();
        StoreSearchCondition condition = new StoreSearchCondition(districtId, nameList, order, pageable);
        List<Store> stores = storeRepository.searchStoresByConditions(condition);
        return stores.stream().map(StoreResponse::fromEntity).toList();
    }

}
