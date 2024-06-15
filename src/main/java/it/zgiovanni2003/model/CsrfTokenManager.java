package it.zgiovanni2003.model;

import java.security.SecureRandom;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CsrfTokenManager {
    private static final String CSRF_TOKEN_SESSION_ATTR_NAME = "csrfToken";

    public static String generateCsrfToken(HttpServletRequest request) {
    	HttpSession session = request.getSession(true);
        byte[] randomBytes = new byte[16];
        new SecureRandom().nextBytes(randomBytes);
        String key=Base64.getEncoder().encodeToString(randomBytes);
        session.setAttribute(CSRF_TOKEN_SESSION_ATTR_NAME, key);
        return key;
    }

    public static String getCsrfTokenFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (String) session.getAttribute(CSRF_TOKEN_SESSION_ATTR_NAME);
        }
        return null;
    }
}
