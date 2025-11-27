package com.umc9th.peter.domain.store.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc9th.peter.domain.store.dto.StoreRequest;
import com.umc9th.peter.domain.store.entity.QStore;
import com.umc9th.peter.domain.store.entity.Store;
import com.umc9th.peter.domain.store.enums.StoreSearchOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StoreQueryDslImpl implements StoreQueryDsl {

    private final JPAQueryFactory queryFactory;

    private final QStore store = QStore.store;

    @Override
    public Page<Store> searchStoresByConditions(StoreRequest.SearchCondition conditions) {

        JPAQuery<Store> contentQuery = queryFactory.selectFrom(store);
        JPAQuery<Long> countQuery = queryFactory.select(store.count()).from(store);

        BooleanBuilder builder = new BooleanBuilder();
        if (conditions.districtId() != null) {
            builder.and(store.district.id.eq(conditions.districtId()));
        }
        if (conditions.nameList() != null && !conditions.nameList().isEmpty()) {
            BooleanBuilder subBuilder = new BooleanBuilder();
            for (String name : conditions.nameList()) {
                subBuilder.or(store.name.like("%" + name + "%"));
            }
            builder.and(subBuilder);
        }
        contentQuery.where(builder);
        countQuery.where(builder);

        if (conditions.order() != null) {
            List<OrderSpecifier<?>> orders = new ArrayList<>();
            if (conditions.order() == StoreSearchOrder.LATEST) {
                orders.add(store.createdAt.desc());
            } else if (conditions.order() == StoreSearchOrder.NAME) {
                orders.add(store.name.asc());
                orders.add(store.createdAt.desc());
            } else {
                throw new IllegalStateException("Invalid order");
            }
            contentQuery.orderBy(orders.toArray(OrderSpecifier<?>[]::new));
        }

        if (conditions.pageable() != null) {
            contentQuery.offset(conditions.pageable().getOffset()).limit(conditions.pageable().getPageSize());
        }

        List<Store> content = contentQuery.fetch();
        Pageable pageable = Optional.ofNullable(conditions.pageable()).orElse(Pageable.unpaged());
        long count = Optional.ofNullable(countQuery.fetchOne()).orElse(0L);

        return new PageImpl<>(content, pageable, count);
    }
}
