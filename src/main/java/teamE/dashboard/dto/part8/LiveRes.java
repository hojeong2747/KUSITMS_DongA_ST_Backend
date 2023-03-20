package teamE.dashboard.dto.part8;


import lombok.*;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiveRes {
//    실시간 진행중 라이브 썸네일
//    라이브 제목
//    시청자 수 ????????
//    라이브 시작-종료시간 ???????

    private String thumbnail;
    private String title;
    private int people;
    private String startTime;
    private String endTime;

}
