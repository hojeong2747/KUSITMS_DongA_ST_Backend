package teamE.dashboard.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Upload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    @Enumerated(EnumType.STRING)
    private UploadStatus uploadStatus;
    private String title;
    private String length;
    private String hospital;
    private String professor;

}
