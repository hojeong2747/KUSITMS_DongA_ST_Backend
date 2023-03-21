package teamE.dashboard.dto.part4;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userInfoByDepartmentRes {
    private String departmentGroup;
    private int percentage;
}
