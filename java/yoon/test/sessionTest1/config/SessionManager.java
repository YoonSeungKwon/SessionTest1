package yoon.test.sessionTest1.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import yoon.test.sessionTest1.dto.reponse.MemberResponse;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    Map<String, Object> sessionStore = new ConcurrentHashMap<>();
    private final String sessionKey = "SESSION_KEY";

    public String createSession(HttpServletRequest request, MemberResponse dto){
        HttpSession session = request.getSession();
        session.setAttribute(sessionKey, dto);
        String uuid = UUID.randomUUID().toString();
        storeSession(uuid, session);
        return uuid;
    }


    public void storeSession(String uuid, HttpSession session){
        sessionStore.put(uuid, session);
    }

    public MemberResponse getValue(String uuid){
        HttpSession session = (HttpSession) sessionStore.get(uuid);
        return (MemberResponse) session.getAttribute(sessionKey);
    }


}
