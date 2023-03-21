package teamE.dashboard.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class WeeklyRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;
    private int month;
    private int week; // 1->첫째주
    private String keyWord;
    private Long hit; // 검색수
    // hit 사용해서 메서드 쓸 때 int 로 바꿨다가 hit 필요 없어서 다시 Long 으로 바꿔놓음.
    private int prevRank; // 이전 등수
    private int curRank; // 현재 등수
    @Enumerated(EnumType.STRING)
    private Status status; // 증가, 감소, 유지



}
