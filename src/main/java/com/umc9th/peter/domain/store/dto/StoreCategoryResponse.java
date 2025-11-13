package com.umc9th.peter.domain.store.dto;

import com.umc9th.peter.domain.store.entity.StoreCategory;

public record StoreCategoryResponse(
        Long id,
        String name
) {

    public static StoreCategoryResponse fromEntity(StoreCategory category) {
        return new StoreCategoryResponse(
                category.getId(),
                category.getName()
        );
    }

}
