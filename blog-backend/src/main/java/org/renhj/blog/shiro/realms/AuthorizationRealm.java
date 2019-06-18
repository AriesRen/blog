package org.renhj.blog.shiro.realms;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.renhj.blog.mapper.RoleMapper;
import org.renhj.blog.mapper.UserMapper;
import org.renhj.blog.mapper.UserRoleMapper;
import org.renhj.blog.pojo.entity.RoleEntity;
import org.renhj.blog.pojo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 统一授权控制策略
 */
@Slf4j
public class AuthorizationRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行shiro授权操作");
        Object principal = principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        if (principal instanceof UserEntity) {
            UserEntity user = (UserEntity) principal;
            if (user != null) {
                List<RoleEntity> roles = userRoleMapper.selectRolesByUserId(user.getUserId());
                if (!CollectionUtils.isEmpty(roles)) {
                    for (RoleEntity role: roles) {
                        info.addRole(role.getName());
                    }
                }
            }
        }

        log.info("获取角色信息如下： " + info.getRoles().toString());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
