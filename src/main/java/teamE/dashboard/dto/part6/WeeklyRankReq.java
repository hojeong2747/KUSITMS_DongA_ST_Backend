package teamE.dashboard.dto.part6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyRankReq {
    private int year;
    private int month;
    private int week;
//    private int hit;
}
