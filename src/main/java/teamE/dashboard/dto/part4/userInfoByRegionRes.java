package teamE.dashboard.dto.part4;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userInfoByRegionRes {
    private String regionGroup;
    private int percentage;
}
