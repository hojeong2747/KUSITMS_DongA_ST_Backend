package teamE.dashboard.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamE.dashboard.security.jwt.*;
import teamE.dashboard.security.user.User;
import teamE.dashboard.security.user.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserTokenService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    @Transactional
    public JwtTokenDto reissue(RequestTokenDto requestTokenDto){

        JwtTokenDto newCreatedToken = new JwtTokenDto();

        //refresh token 만료
        if(!jwtTokenProvider.validateToken(requestTokenDto.getRefreshToken())){
            throw new IllegalStateException("refresh error");
        }

        //refresh token에서 PK 가져오기
        String accessToken = requestTokenDto.getRefreshToken();
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        //user pk로 유저 검색/ repo에 저장된 토큰 있는지 검색
        User member = userRepository.findByUsername(authentication.getName())
                .orElseThrow(IllegalStateException::new);

        RefreshToken refreshToken = refreshTokenRepository.findByUsername(member.getUsername())
                .orElseThrow(IllegalStateException::new);

        if(!refreshToken.getToken().equals(requestTokenDto.getRefreshToken()))
            throw new IllegalStateException("not equal refresh");

        //토큰 재발급 및 리프레쉬 토큰 저장
        newCreatedToken.setAccessToken(jwtTokenProvider.createToken(member.getUsername(), member.getRoles()));
        newCreatedToken.setRefreshToken(jwtTokenProvider.createRefreshToken(member.getUsername(), member.getRoles()));
        newCreatedToken.setDate(jwtTokenProvider.jwtValidDate());
        RefreshToken updateRefreshToken = refreshToken.updateToken(newCreatedToken.getRefreshToken());
        refreshTokenRepository.save(updateRefreshToken);

        return newCreatedToken;
    }

}
