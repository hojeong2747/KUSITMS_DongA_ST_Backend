package teamE.dashboard.security;

import lombok.Data;

@Data
public class UserJoinDto {

    private String userName;

    private String loginId;

    private String loginPw;

    private String userEmail;

    private String userPhone;

    private String  userBirth;

    private String userJob;

    private String nickname;
}