package teamE.dashboard.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    // JWT 토큰이 저장된 Authorization 헤더의 prefix를 체크하여 JWT 토큰을 추출하는 로직이 구현되어 있습니다.
    // 반면, JwtAuthenticationFilter 클래스에서는 JWT 토큰 추출 로직은 JwtTokenProvider 클래스에 구현되어 있으며,
    // resolveToken 메소드를 호출하여 JWT 토큰을 추출합니다.
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 JWT 를 받아옵니다.
//        log.info("2. AuthenticationFilter가 이 요청을 가로챕니다. 이 때 가로챈 정보를 통해 토큰(UsernamePasswordAuthenticationToken이라는 인증용 객체)를 생성합니다.");
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        // 유효한 토큰인지 확인합니다.
//        log.info("3. AuthenticationManager의 구현체인 ProviderManager에게 토큰(UsernamePasswordAuthenticationToken 객체)를 전달합니다.");
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
//            log.info("8. 인증이 완료되면 권한 등의 사용자 정보를 담은 Authentication 객체를 반환합니다.\n");
            // SecurityContext 에 Authentication 객체를 저장합니다.
//            log.info("10. Authentication 객체를 SecurityContext에 저장합니다.");
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}