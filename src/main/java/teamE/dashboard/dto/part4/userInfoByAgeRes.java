package teamE.dashboard.dto.part4;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userInfoByAgeRes {
    private String ageGroup;
    private int percentage;
}
