package teamE.dashboard.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Funnels funnels;

    @Enumerated(EnumType.STRING)
    private Age age;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Region region;
    
    // 유저데이터 : doctor id, 유입경로, 연령대, 진료과, 지역


    public Doctor() {
    }
}
