package org.renhj.blog.shiro;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.joda.time.DateTime;
import org.renhj.blog.pojo.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


@Slf4j
public class SessionControlFilter extends AccessControlFilter {
    private String kickoutUrl; // 踢出之后的地址
    private boolean kickoutAfter = false; // 踢出之前登录的用户
    private int maxSession = 1; // 同一个账号的最大会话数 默认是1
    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;


    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", httpServletRequest.getMethod());
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(200);
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);

        // 没有登录 过
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            return true;
        }

        Session session = subject.getSession();
        UserEntity userEntity = (UserEntity) subject.getPrincipal();
        String username = userEntity.getUsername();
        Serializable sessionId = session.getId();

        // 读取缓存
        Deque<Serializable> deque = cache.get(username);

        // 如果用户没有session队列，也即是没有登录过，缓存中没有
        if (deque == null) {
            deque = new LinkedList<Serializable>();
        }

        if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            // 将sessionId存入队列
            deque.push(sessionId);
            // 将sessionId缓存
            cache.put(username, deque);
        }

        // 如果队列里的sessionId的数量超出最大会话数，开始踢人
        while (deque.size() > maxSession) {
            Serializable kickoutSessionId = null;
            if (kickoutAfter) { // 踢出后者
                kickoutSessionId = deque.removeFirst();
                cache.put(username, deque);
            } else { // 踢出前者
                kickoutSessionId = deque.removeLast();
                cache.put(username, deque);
            }

            try {
                // 获取被踢出的sessionId的session对象
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if (kickoutSession != null) {
                    // 设置kickout 为 true标识踢出
                    kickoutSession.setAttribute("kickout", true);
                    kickoutSession.setAttribute("kickout_time", new DateTime());
                }
                log.info("踢出用户： {}", kickoutSessionId);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 如果被踢出了，直接退出
        Boolean marker = (Boolean) session.getAttribute("kickout");
        if (marker != null && marker) {
            try {
                subject.logout();
            }catch (Exception e) {
                e.printStackTrace();
            }
            saveRequest(request);
            // 判断是不是ajax请求
            Map<String, String> resultMap = new HashMap<>();
            if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest)request).getHeader("X-Request-With"))) {

                resultMap.put("code", "300");
                resultMap.put("msg", "您已在其他地方登录，请确认是您登录，或者更改密码");
                out(response, resultMap);
            }else {
                // 重定向
                WebUtils.issueRedirect(request, response, kickoutUrl);
            }
            return false;
        }
        return true;
    }

    private void out(ServletResponse response, Map<String, String> resultMap) {
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(resultMap));
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println("KickoutFilter 异常");
            e.printStackTrace();
        }
    }

    public String getKickoutUrl() {
        return kickoutUrl;
    }

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public boolean isKickoutAfter() {
        return kickoutAfter;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public int getMaxSession() {
        return maxSession;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public Cache<String, Deque<Serializable>> getCache() {
        return cache;
    }

    public void setCache(CacheManager cache) {
        this.cache = cache.getCache("shiro_redis_cache");
    }
}
