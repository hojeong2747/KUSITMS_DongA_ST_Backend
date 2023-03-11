package teamE.dashboard.security;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Slf4j
@Component
public class CustomHttpSessionListener implements HttpSessionListener {

    private static final Map<String, HttpSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        sessions.put(session.getId(), session);
        log.info("sessions - SESSION CREATED : {}", session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        sessions.remove(session.getId());
    }

    public static List<String> getSessions() {

        Collection<HttpSession> values = sessions.values();

        List<String> names = new ArrayList<>();

        for (HttpSession value : values) {

            SecurityContext springSecurityContextKey = (SecurityContext) value.getAttribute(SPRING_SECURITY_CONTEXT_KEY);
            names.add(springSecurityContextKey.getAuthentication().getName());
        }

        return names;
    }

    public static List<String> getUsers() {
        return null;
    }

}