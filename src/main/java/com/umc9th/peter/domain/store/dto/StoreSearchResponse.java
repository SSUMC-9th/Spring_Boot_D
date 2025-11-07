package com.umc9th.peter.domain.store.dto;

import java.util.List;

public record StoreSearchResponse(
        List<StoreResponse> content,
        long total
) {

}
