package teamE.dashboard.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String length;
    private String date;
    private int hit;
    private String thumbnail;

    private int status; // 0 진료 1 비진료

    private String age;
    private String link;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Disease disease;

    @Enumerated(EnumType.STRING)
    private NonMedical nonMedical;


    public Video() {
    }


}
