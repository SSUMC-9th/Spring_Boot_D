package com.umc9th.peter.domain.store.dto;

import com.umc9th.peter.domain.store.enums.StoreSearchOrder;
import org.springframework.data.domain.Pageable;

import java.util.List;

public record StoreSearchCondition(Long districtId, List<String> nameList, StoreSearchOrder order, Pageable pageable) {
}
