package teamE.dashboard.dto.part7;


import lombok.*;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoRes {
    private String username;
    private String profileImg;

    private String date;
    private int status;

    private String content;
}
