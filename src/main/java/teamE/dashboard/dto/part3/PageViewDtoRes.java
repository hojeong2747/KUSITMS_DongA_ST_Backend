package teamE.dashboard.dto.part3;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageViewDtoRes implements Comparable<PageViewDtoRes> {
    private int date; // String -> day 만 int 로 전송
    private String d;
    @JsonProperty("페이지뷰")
    private Long pageView;

    public PageViewDtoRes(String d, Long pageView) {
        this.d = d;
        this.pageView = pageView;
    }

    @Override
    public int compareTo(PageViewDtoRes p) {
        return date - p.date;
    }
}
