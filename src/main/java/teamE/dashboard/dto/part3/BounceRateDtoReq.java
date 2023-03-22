package teamE.dashboard.dto.part3;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BounceRateDtoReq {
    @ApiParam(value = "날짜", type = "String", required = true, example = "2023-03-15")
    private String date;
}
