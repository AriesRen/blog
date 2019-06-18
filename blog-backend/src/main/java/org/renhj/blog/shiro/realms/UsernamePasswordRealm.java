package org.renhj.blog.shiro.realms;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.renhj.blog.mapper.UserMapper;
import org.renhj.blog.pojo.entity.UserEntity;
import org.renhj.blog.pojo.enums.LoginType;
import org.renhj.blog.service.UserService;
import org.renhj.blog.shiro.CustomLoginToken;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户名密码登录Realm
 */
@Slf4j
public class UsernamePasswordRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getName() {
        return LoginType.USER_PASSWORD.getType();
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        if (token instanceof CustomLoginToken) {
            return ((CustomLoginToken) token).getLoginType() == LoginType.USER_PASSWORD;
        } else {
            return false;
        }
    }

    @Override
    public void setAuthorizationCacheName(String authorizationCacheName) {
        super.setAuthorizationCacheName(authorizationCacheName);
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 授权先不做，在AuthorizationRealm 统一做授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 身份认证 用户名密码认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("用户名密码登录 ---- 认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        UserEntity user = userMapper.selectOneByUsername(username);

        if (user != null) {
            if (user.getStatus() != 1) {
                log.error("用户状态{} 不正常！", user.getStatus());
                throw new DisabledAccountException();
            }
            return new SimpleAuthenticationInfo(
                    user,
                    user.getPassword(),
                    null,
                    getName()
            );
        }
        throw new UnknownAccountException();
    }
}
