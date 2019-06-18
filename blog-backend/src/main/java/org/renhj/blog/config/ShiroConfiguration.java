package org.renhj.blog.config;

import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.renhj.blog.pojo.enums.LoginType;
import org.renhj.blog.shiro.CredentialsMatcher;
import org.renhj.blog.shiro.CustomSessionManager;
import org.renhj.blog.shiro.MyModularRealmAuthenticator;
import org.renhj.blog.shiro.SessionControlFilter;
import org.renhj.blog.shiro.listenter.OnlineSessionListener;
import org.renhj.blog.shiro.realms.AuthorizationRealm;
import org.renhj.blog.shiro.realms.UserPhoneRealm;
import org.renhj.blog.shiro.realms.UsernamePasswordRealm;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.swing.table.TableRowSorter;
import java.util.*;

@Configuration
public class ShiroConfiguration {
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;


    @Bean
    public ShiroFilterFactoryBean factoryBean(SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        bean.setLoginUrl("/auth/unauth");
        bean.setUnauthorizedUrl("/auth/unlogin");

        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("kickout", sessionControlFilter());

        bean.setFilters(filterMap);

        Map<String, String> filterChain = new LinkedHashMap<>();
        filterChain.put("/auth/**", "anon");
        filterChain.put("/session/**", "anon");
//        filterChain.put("/**", "authc,kickout");
        bean.setFilterChainDefinitionMap(filterChain);
        return bean;
    }

    @Bean
    public SessionControlFilter sessionControlFilter() {
        SessionControlFilter sessionControlFilter = new SessionControlFilter();
        //
        sessionControlFilter.setCache(cacheManager());
        sessionControlFilter.setSessionManager(sessionManager());
        sessionControlFilter.setKickoutAfter(false);
        sessionControlFilter.setMaxSession(1);
        sessionControlFilter.setKickoutUrl("/auth/kickout");
        return sessionControlFilter;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setAuthenticator(modularRealmAuthenticator());

        List<Realm> realms = new ArrayList<>();
        // 统一角色授权realm
//        realms.add(authorizingRealm());
        // 用户名密码登录realm
        realms.add(usernamePasswordRealm());
        // 手机号验证码登录realm
        realms.add(userPhoneRealm());

        manager.setRealms(realms);
        // 缓存管理 redis
        manager.setCacheManager(cacheManager());
        // session 管理 redis
        manager.setSessionManager(sessionManager());
        return manager;
    }


    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator() {
        MyModularRealmAuthenticator authenticator = new MyModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return authenticator;
    }

    @Bean
    public AuthorizingRealm authorizingRealm() {
        AuthorizingRealm authorizingRealm = new AuthorizationRealm();
        authorizingRealm.setName(LoginType.COMMON.getType());
        return authorizingRealm;
    }

    @Bean
    public UsernamePasswordRealm usernamePasswordRealm() {
        UsernamePasswordRealm realm = new UsernamePasswordRealm();
        realm.setName(LoginType.USER_PASSWORD.getType());
        // 密码校验器
        realm.setCredentialsMatcher(matcher());
        return realm;
    }

    @Bean
    public UserPhoneRealm userPhoneRealm() {
        UserPhoneRealm phoneRealm = new UserPhoneRealm();
        phoneRealm.setName(LoginType.USER_PHONE.getType());
        return phoneRealm;
    }

    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager manager = new RedisCacheManager();
        manager.setRedisManager(redisManager());
        manager.setPrincipalIdFieldName("userId");
        manager.setKeyPrefix("SPRING_CACHE:");
        return manager;
    }

    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisHost + ":" + redisPort);
//        redisManager.setPassword(redisPassword);
        redisManager.setTimeout(18000);
        return redisManager;
    }

    @Bean
    public CredentialsMatcher matcher() {
        return new CredentialsMatcher();
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO dao = new RedisSessionDAO();
        dao.setRedisManager(redisManager());
        dao.setKeyPrefix("SPRING_SESSION:");
        return dao;
    }

    @Bean
    public SessionManager sessionManager() {
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(sessionListener());
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        return sessionManager;

//        CustomSessionManager sessionManager = new CustomSessionManager();
//        sessionManager.setSessionListeners(listeners);
//        sessionManager.setSessionDAO(redisSessionDAO());
//        sessionManager.setSessionIdCookieEnabled(false);
//        sessionManager.setSessionIdUrlRewritingEnabled(false);
//        sessionManager.setDeleteInvalidSessions(true);
////        sessionManager.setSessionIdCookie(cookie);
//        sessionManager.setDeleteInvalidSessions(true);
//        sessionManager.setSessionValidationSchedulerEnabled(true);
//        sessionManager.setGlobalSessionTimeout(180000);
//        return sessionManager;
    }

    @Bean
    public SessionListener sessionListener() {
        return new OnlineSessionListener();
    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
