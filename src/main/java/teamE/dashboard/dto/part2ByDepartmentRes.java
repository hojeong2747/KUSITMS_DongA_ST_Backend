package teamE.dashboard.dto;

import lombok.*;
import teamE.dashboard.entity.Department;

@Getter @Setter
@ToString @Data
@AllArgsConstructor
@NoArgsConstructor
public class part2ByDepartmentRes {

    private String department;
    private String hits;


}
