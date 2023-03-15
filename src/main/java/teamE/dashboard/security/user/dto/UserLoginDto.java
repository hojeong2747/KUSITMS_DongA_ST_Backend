package teamE.dashboard.security.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserLoginDto {

    private String username;

    private String password;


}