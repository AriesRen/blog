package org.renhj.blog.config;

import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.filter.DelegatingFilterProxy;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
@Configuration
public class HttpSessionConfiguration {

    @Bean
    public CookieHttpSessionIdResolver cookieHttpSessionIdResolver() {
        CookieHttpSessionIdResolver resolver = new CookieHttpSessionIdResolver();
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("session_id");
        serializer.setCookieMaxAge(3600);
        resolver.setCookieSerializer(serializer);
        return resolver;
    }

}
