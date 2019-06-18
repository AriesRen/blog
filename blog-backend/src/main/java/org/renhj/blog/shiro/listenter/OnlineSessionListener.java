package org.renhj.blog.shiro.listenter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
public class OnlineSessionListener implements SessionListener {
    private final AtomicInteger onLine = new AtomicInteger(0);


    @Override
    public void onStart(Session session) {
        onLine.incrementAndGet();
        log.info("当前在线人数：{}", onLine);
    }

    @Override
    public void onStop(Session session) {
        onLine.decrementAndGet();
        log.info("当前在线人数：{}", onLine);
    }

    @Override
    public void onExpiration(Session session) {
        onLine.decrementAndGet();
        log.info("当前在线人数：{}", onLine);
    }

    public AtomicInteger getOnLine() {
        return onLine;
    }
}
