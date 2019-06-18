package org.renhj.blog.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.renhj.blog.pojo.enums.LoginType;

public class CustomLoginToken extends UsernamePasswordToken {
    // 登录方式
    private LoginType loginType;
    // 微信code
    private String code;

    public CustomLoginToken() {
    }
    public CustomLoginToken(LoginType loginType, final String username, final String password){
        super(username, password);
        this.loginType = loginType;
    }

    public CustomLoginToken(LoginType loginType, final String username, final String password, String code){
        super(username, password);
        this.loginType = loginType;
        this.code = code;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
