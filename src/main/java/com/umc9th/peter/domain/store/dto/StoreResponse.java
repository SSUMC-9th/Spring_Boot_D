package com.umc9th.peter.domain.store.dto;

import com.umc9th.peter.domain.store.entity.Store;
import com.umc9th.peter.domain.store.entity.StoreCategory;

import java.util.List;

public class StoreResponse {

    public record StoreDto(
            Long id,
            String name,
            String address,
            CategoryDto category
    ) {

        public static StoreDto fromEntity(Store store) {
            return new StoreDto(
                    store.getId(),
                    store.getName(),
                    store.getAddress(),
                    CategoryDto.fromEntity(store.getStoreCategory())
            );
        }

    }

    public record CategoryDto(
            Long id,
            String name
    ) {

        public static CategoryDto fromEntity(StoreCategory category) {
            return new CategoryDto(
                    category.getId(),
                    category.getName()
            );
        }

    }

    public record SearchDto(
            List<StoreResponse.StoreDto> content,
            long total
    ) {

    }

}
