package teamE.dashboard.entity;

import lombok.Getter;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;

@Getter
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

    private int prevRank; // 이전 등수
    private int curRank; // 현재 등수
    @Enumerated(EnumType.STRING)
    private Status status; // 증가, 감소, 유지



}
