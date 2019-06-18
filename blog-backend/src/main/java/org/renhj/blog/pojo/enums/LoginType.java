package org.renhj.blog.pojo.enums;

public enum LoginType {
    /**
     *通用登录
     */
    COMMON("common_realm"),
    /**
     * 用户名密码登录
     */
    USER_PASSWORD("user_password_realm"),
    /**
     * 手机验证码登录
     */
    USER_PHONE("user_phone_realm"),
    /**
     * 邮箱登录
     */
    EMAIL_LOGIN("email_login_realm"),
    /**
     * 微信登录
     */
    WECHAT_LOGIN("wechat_login_realm");

    private String type;

    LoginType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
}
