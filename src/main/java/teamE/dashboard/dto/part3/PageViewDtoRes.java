package teamE.dashboard.dto.part3;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageViewDtoRes {
    private int date; // String -> day 만 int 로 전송
    private Long activeUserCount;
}
