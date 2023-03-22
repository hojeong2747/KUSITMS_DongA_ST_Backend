package teamE.dashboard.dto.part6;

import lombok.*;
import teamE.dashboard.entity.Status;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyRankRes {

    private int curRank;
    private String keyWord;
    private String status;
}
