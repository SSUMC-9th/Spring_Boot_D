package com.umc9th.peter.domain.store.dto;

import com.umc9th.peter.domain.store.entity.Store;

public record StoreResponse(
        Long id,
        String name,
        String address,
        StoreCategoryResponse category
) {

    public static StoreResponse fromEntity(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getName(),
                store.getAddress(),
                StoreCategoryResponse.fromEntity(store.getStoreCategory())
        );
    }

}
