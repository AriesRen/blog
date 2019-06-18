package org.renhj.blog.shiro.realms;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.renhj.blog.mapper.UserMapper;
import org.renhj.blog.pojo.entity.UserEntity;
import org.renhj.blog.pojo.enums.LoginType;
import org.renhj.blog.redis.RedisDao;
import org.renhj.blog.shiro.CustomLoginToken;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserPhoneRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisDao redisDao;

    @Override
    public String getName() {
        return LoginType.USER_PHONE.getType();
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        if (token instanceof CustomLoginToken) {
            return ((CustomLoginToken) token).getLoginType() == LoginType.USER_PHONE;
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

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 身份认证 手机号验证码登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("身份认证---手机验证码登录");
        CustomLoginToken token = (CustomLoginToken) authenticationToken;
        String phone = token.getUsername();
        String code = String.valueOf(token.getPassword());
        String redisCode = (String) redisDao.get(phone);
        redisCode = "123";
        if (!redisCode.equalsIgnoreCase(code)) {
            throw new IncorrectCredentialsException();
        }
        UserEntity userEntity = userMapper.selectOneByPhone(phone);

        if (userEntity == null) {
            throw new UnknownAccountException();
        }

        if (userEntity.getStatus() == 0 || userEntity.getStatus() == 2) {
            throw new DisabledAccountException();
        }

        return new SimpleAuthenticationInfo(
                userEntity,
                redisCode,
                getName()
        );
    }
}
