package com.umc9th.peter.domain.mission.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc9th.peter.domain.mission.dto.MissionRequest;
import com.umc9th.peter.domain.mission.entity.Mission;
import com.umc9th.peter.domain.mission.entity.QDistrict;
import com.umc9th.peter.domain.mission.entity.QMission;
import com.umc9th.peter.domain.mission.entity.mapping.QMissionDistrict;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MissionQueryDslImpl implements MissionQueryDsl {

    private final JPAQueryFactory queryFactory;

    private final QMission mission = QMission.mission;

    private final QDistrict district = QDistrict.district;

    private final QMissionDistrict missionDistrict = QMissionDistrict.missionDistrict;

    @Override
    public Page<Mission> searchMissionsByConditions(
            MissionRequest.SearchConditionDto conditions,
            Pageable pageable
    ) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(missionDistrict.district.id.eq(conditions.districtId()));
        if (conditions.storeId() != null) {
            builder.and(mission.store.id.eq(conditions.storeId()));
        }

        List<Mission> missions = queryFactory
                .selectFrom(mission)
                .distinct()
                .join(mission.missionDistrictList, missionDistrict)
                .join(missionDistrict.district, district)
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        long count = Optional.ofNullable(queryFactory
                        .select(mission.countDistinct())
                        .from(mission)
                        .join(mission.missionDistrictList, missionDistrict)
                        .join(missionDistrict.district, district)
                        .where(builder)
                        .fetchOne())
                .orElse(0L);

        return new PageImpl<>(missions, pageable, count);
    }

}
