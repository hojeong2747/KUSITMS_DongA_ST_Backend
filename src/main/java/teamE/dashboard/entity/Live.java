package teamE.dashboard.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Live {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String length;
    private String date;
    private int hit;
    private String thumbnail;

    private int status; // 라이브 상태 0  1 실행중

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Disease disease;

    private int people;
    private String startTime;
    private String endTime;


    public Live() {
    }
}
