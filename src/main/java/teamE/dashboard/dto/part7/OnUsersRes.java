package teamE.dashboard.dto.part7;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnUsersRes {
    private List<String> onUsers;

    private int userNum;

}
