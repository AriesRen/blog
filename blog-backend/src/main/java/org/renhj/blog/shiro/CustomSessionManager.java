package org.renhj.blog.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

@Slf4j
public class CustomSessionManager extends DefaultWebSessionManager {
    private static final String AUTHORIZATION = "token";
    private static final String REFERENCED_SESSION_ID_SOURCE = "stateless request";

    public CustomSessionManager() {
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 获取请求头 或者请求参数中的token
//        String id = StringUtils.isEmpty(WebUtils.toHttp(request).getHeader(AUTHORIZATION))
//                ? request.getParameter(AUTHORIZATION) : WebUtils.toHttp(request).getHeader(AUTHORIZATION);

        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);

        if (!StringUtils.isEmpty(sessionId)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        }
        return super.getSessionId(request, response);
    }


//    @Override
//    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
//        Serializable sessionId = getSessionId(sessionKey);
//
//        ServletRequest request = null;
//
//        if (sessionKey instanceof WebSessionKey) {
//            request = ((WebSessionKey) sessionKey).getServletRequest();
//        }
//        if (request != null && null != sessionId) {
//            Object sessionObj = request.getAttribute(sessionId.toString());
//            if (sessionObj != null) {
//                return (Session) sessionObj;
//            }
//        }
//
//        Session session = super.retrieveSession(sessionKey);
//        if (request != null && null != sessionId) {
//            request.setAttribute(sessionId.toString(), session);
//        }
//        return session;
//    }
}
