package com.umc9th.peter.domain.store.controller;

import com.umc9th.peter.domain.store.dto.StoreResponse;
import com.umc9th.peter.domain.store.enums.StoreSearchOrder;
import com.umc9th.peter.domain.store.service.StoreService;
import com.umc9th.peter.global.api.ApiResponse;
import com.umc9th.peter.global.api.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ApiResponse<StoreResponse.SearchDto> getStores(
            @RequestParam(required = false) Long districtId,
            @RequestParam(required = false) String keywords,
            @RequestParam(required = false) StoreSearchOrder order,
            Pageable pageable
    ) {
        StoreResponse.SearchDto stores = storeService.getStores(districtId, keywords, order, pageable);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                stores
        );
    }

}
