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


    public static ReviewFilterDto ofScore(Integer score) {
        if (score == null || score < 1 || score > 5) {
            return ReviewFilterDto.builder().build();
        }

        if (score == 5) {
            // 5점은 정확히 5.0만
            return ReviewFilterDto.builder()
                    .minScore(5.0f)
                    .maxScore(5.0f)
                    .build();
        } else {
            // 1~4점 점수대
            return ReviewFilterDto.builder()
                    .minScore(score.floatValue())
                    .maxScore(score + 0.99f)
                    .build();
        }
    }

    /**
     * 가게ID와 별점을 함께 필터링
     */
    public static ReviewFilterDto of(Long storeId, Integer score) {
        ReviewFilterDto filter = ofScore(score);
        return ReviewFilterDto.builder()
                .storeId(storeId)
                .minScore(filter.getMinScore())
                .maxScore(filter.getMaxScore())
                .build();
    }
}
