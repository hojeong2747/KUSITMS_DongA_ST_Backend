package teamE.dashboard.dto.part3;

import lombok.AllArgsConstructor;
import lombok.Data;
import teamE.dashboard.service.BounceRateService;

@Data
//@AllArgsConstructor
public class BounceRateDtoRes implements Comparable<BounceRateDtoRes> {
    private int date;

    private String d;

    private Long nv;
    private Long rv;
    private Long uv;

    private int rvPercentage; // 이탈률 퍼센트 -> 우선 double 형으로 계산 -> 재방문률
    private int nvPercentage; // 신규방문률

    public BounceRateDtoRes(String d, Long nv, Long rv, Long uv, int rvPercentage, int nvPercentage) {
        this.d = d;
        this.nv = nv;
        this.rv = rv;
        this.uv = uv;
        this.rvPercentage = rvPercentage;
        this.nvPercentage = nvPercentage;
    }

    @Override
    public int compareTo(BounceRateDtoRes b) {
        return date - b.date;
    }

    public String toString() {
        return date+"일";
    }
}
