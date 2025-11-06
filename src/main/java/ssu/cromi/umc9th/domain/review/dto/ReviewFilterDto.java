package ssu.cromi.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewFilterDto {
    private Long storeId;        // 가게별 필터링 (Optional)
    private Float minScore;      // 최소 별점 (Optional)
    private Float maxScore;      // 최대 별점 (Optional)

    // 별점 범위를 쉽게 설정하기 위한 헬퍼 메서드
    public static ReviewFilterDto of5Star() {
        return ReviewFilterDto.builder()
                .minScore(5.0f)
                .maxScore(5.0f)
                .build();
    }

    public static ReviewFilterDto of4Star() {
        return ReviewFilterDto.builder()
                .minScore(4.0f)
                .maxScore(4.99f)
                .build();
    }

    public static ReviewFilterDto of3Star() {
        return ReviewFilterDto.builder()
                .minScore(3.0f)
                .maxScore(3.99f)
                .build();
    }
}
