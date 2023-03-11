package teamE.dashboard.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserLoginDto {

    private String loginId;

    private String loginPw;


}