package com.umc9th.peter.domain.store.service;

import com.umc9th.peter.domain.store.dto.StoreRequest;
import com.umc9th.peter.domain.store.dto.StoreResponse;
import com.umc9th.peter.domain.store.entity.Store;
import com.umc9th.peter.domain.store.enums.StoreSearchOrder;
import com.umc9th.peter.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    public StoreResponse.SearchDto getStores(
            Long districtId,
            String keywords,
            StoreSearchOrder order,
            Pageable pageable
    ) {
        List<String> nameList = Arrays.stream(Optional.ofNullable(keywords).orElse("").trim().split("\\s+")).toList();
        StoreRequest.SearchCondition condition = new StoreRequest.SearchCondition(districtId, nameList, order, pageable);

        Page<Store> stores = storeRepository.searchStoresByConditions(condition);
        List<StoreResponse.StoreDto> content = stores.getContent().stream()
                .map(StoreResponse.StoreDto::fromEntity)
                .toList();
        long total = stores.getTotalElements();

        return new StoreResponse.SearchDto(content, total);
    }

}
