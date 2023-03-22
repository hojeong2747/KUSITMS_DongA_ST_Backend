package teamE.dashboard.dto.part3;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageViewDtoRes implements Comparable<PageViewDtoRes> {
    private int date; // String -> day 만 int 로 전송
    private Long activeUserCount;

    @Override
    public int compareTo(PageViewDtoRes p) {
        return date - p.date;
    }
}
