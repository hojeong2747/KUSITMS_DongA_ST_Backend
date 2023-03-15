package teamE.dashboard.security.sesssion;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.*;

@Slf4j
@Component
public class SessionUserCounter implements HttpSessionListener {

    public static int count;

    public static int getCount() {
        return count;
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        log.info("session made");
        //  세션이 생성될 때 세션객체를 꺼내옴.
        HttpSession session = event.getSession();
        count++;
        log.error("\n\tSESSION CREATED : {}, TOTAL ACCESSER : {}", session.getId(), count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        // 세션이 소멸될 때
        log.info("session destroy");

        count--;
        if (count < 0) count = 0;

        HttpSession session = event.getSession();
        log.error("\n\tSESSION DESTROYED : {}, TOTAL ACCESSER : {}");
    }



}
