package teamE.dashboard.dto.part9;

import lombok.*;
import teamE.dashboard.entity.UploadStatus;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadDtoRes {
    private UploadStatus uploadStatus;
    private String title;
    private String length;
    private String hospital;
    private String professor;
}
