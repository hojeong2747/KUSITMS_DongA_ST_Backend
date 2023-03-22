package teamE.dashboard.dto.part3;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BounceRateDtoRes implements Comparable<BounceRateDtoRes> {
    private int date;
    private Long exitUserCount;

    private Long totalUserCount;

    private double bounceRate; // 이탈률 퍼센트 -> 우선 double 형으로 계산

    @Override
    public int compareTo(BounceRateDtoRes b) {
        return date - b.date;
    }
}
