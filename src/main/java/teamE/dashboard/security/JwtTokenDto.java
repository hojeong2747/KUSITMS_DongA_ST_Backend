package teamE.dashboard.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class JwtTokenDto {
    //access Token
    private String accessToken;

    private String refreshToken;

    private Date date;

    public void updateRefreshToken(RefreshToken refreshToken){
        this.refreshToken = refreshToken.getToken();
    }
}
