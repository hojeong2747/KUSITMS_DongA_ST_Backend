package teamE.dashboard.dto.part4;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userInfoByFunnelsRes {
    private String funnelsGroup;
    private int percentage;
}
