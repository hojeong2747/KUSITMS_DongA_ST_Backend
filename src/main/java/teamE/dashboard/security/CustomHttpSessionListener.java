package teamE.dashboard.security;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Component
public class CustomHttpSessionListener implements HttpSessionListener {

    private static final Map<String, HttpSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        sessions.put(session.getId(), session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        sessions.remove(session.getId());
    }

    public static Collection<HttpSession> getSessions() {
        return sessions.values();
    }

}