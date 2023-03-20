package teamE.dashboard.dto.part5;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRes {
//    관리자 이름,프로필이미지,직급,인증된 계정 여부

    private String name;
    private String role;
    private String profileImg;
    private String auth;
}

