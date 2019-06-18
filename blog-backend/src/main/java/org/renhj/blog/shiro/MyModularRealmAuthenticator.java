package org.renhj.blog.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.renhj.blog.pojo.enums.LoginType;

import java.util.Collection;
import java.util.HashMap;


/**
 * 自定义多realm策略
 */
public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        assertRealmsConfigured();

        /* 所有realm */
        Collection<Realm> realms = getRealms();
        /* 登录类型对应的所有的Realm */
        HashMap<String, Realm> realmHashMap = new HashMap<>();

        for (Realm realm: realms) {
            realmHashMap.put(realm.getName(), realm);
        }

        CustomLoginToken token = (CustomLoginToken) authenticationToken;
        /* 登录类型 */
        LoginType loginType = token.getLoginType();

        if (realmHashMap.get(loginType.getType()) != null) {
            return doSingleRealmAuthentication(realmHashMap.get(loginType.getType()), token);
        }else {
            doMultiRealmAuthentication(realms, token);
        }

        return super.doAuthenticate(authenticationToken);
    }
}
