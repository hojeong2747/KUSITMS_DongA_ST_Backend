package teamE.dashboard.dto.part3;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BounceRateDtoRes implements Comparable<BounceRateDtoRes> {
    private int date;
    private Long nv;
    private Long rv;
    private Long uv;

    private int rvPercentage; // 이탈률 퍼센트 -> 우선 double 형으로 계산 -> 재방문률
    private int nvPercentage; // 신규방문률

    @Override
    public int compareTo(BounceRateDtoRes b) {
        return date - b.date;
    }
}
