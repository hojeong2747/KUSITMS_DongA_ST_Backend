package teamE.dashboard.security;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = {"1.User"})
@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserTokenService userTokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;

    // 회원가입
    @ApiOperation(value = "회원 등록", notes = "회원 가입")
    @PostMapping("/join")
    public Long join(@ApiParam(value = "유저", required = true)
                         @RequestBody Map<String, String> user) {
        return userRepository.save(User.builder()
                .email(user.get("email"))
                .password(passwordEncoder.encode(user.get("password")))
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
//                .roles(Collections.singletonList("USER")) // 최초 가입시 USER 로 설정
                .build()).getId();
    }

    // 로그인
    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/login")
    public String login(@ApiParam(value = "유저", required = true)
                            @RequestBody Map<String, String> user) {
        User member = userRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }

    @GetMapping("/login/form")
    public String getLogin() {
        return "login/loginForm";
    }

    @PostMapping("/login/form")
    public JwtTokenDto login(@RequestBody UserLoginDto user, HttpSession session) {
        User member = userRepository.findByEmail(user.getLoginId())
                .orElseThrow(() -> new IllegalStateException());
        if (!passwordEncoder.matches(user.getLoginPw(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        // session
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getLoginId(), user.getLoginPw());
        Authentication authenticate = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
//        session.setAttribute(user.getLoginId(),user.getLoginPw());
//
        log.info("session made " +session.getId());
        log.info(String.valueOf(session.isNew()));


        // jwt
        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        RefreshToken oldRefreshToken = refreshTokenRepository.findByEmail(member.getEmail())
                .orElseGet(RefreshToken::new);
        oldRefreshToken.setEmail(member.getEmail());

        if (oldRefreshToken == null) {
            log.info("oldRefresh");
            oldRefreshToken.setToken(jwtTokenProvider.createRefreshToken(member.getEmail(), member.getRoles()));
            refreshTokenRepository.save(oldRefreshToken);
            jwtTokenDto.updateRefreshToken(oldRefreshToken);
        } else {
            log.info("not oldRefresh");
            jwtTokenDto.setAccessToken(jwtTokenProvider.createToken(member.getEmail(), member.getRoles()));
            jwtTokenDto.setRefreshToken(jwtTokenProvider.createRefreshToken(member.getEmail(), member.getRoles()));
            jwtTokenDto.setDate(jwtTokenProvider.jwtValidDate());
            RefreshToken refreshToken = new RefreshToken(member.getEmail(), jwtTokenDto.getRefreshToken());
            oldRefreshToken.updateToken(refreshToken.getToken());
            refreshTokenRepository.save(oldRefreshToken);
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        log.info( "welcome"+ ((UserDetails) principal).getUsername() );

        return jwtTokenDto;
    }


    @PostMapping("/user/resource")
    public String hi() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        return ((UserDetails) principal).getUsername();
    }

    @PostMapping("/reissue")
    public JwtTokenDto refreshToken(@RequestHeader(value = "ACCESS-TOKEN") String accessToken,
                                    @RequestHeader(value = "REFRESH-TOKEN") String refreshToken) {
        RequestTokenDto requestTokenDto = new RequestTokenDto(accessToken, refreshToken);

        JwtTokenDto jwtTokenDto = userTokenService.reissue(requestTokenDto);

        return jwtTokenDto;
    }


    @GetMapping("/count")
    public List<String> getCount() {
//        return SessionUserCounter.getCount();
        return CustomHttpSessionListener.getSessions();
    }

}