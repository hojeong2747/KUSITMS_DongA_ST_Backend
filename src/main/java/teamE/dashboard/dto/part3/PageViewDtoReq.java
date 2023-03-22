package teamE.dashboard.dto.part3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageViewDtoReq {
    private int category;
    private String date;
}
