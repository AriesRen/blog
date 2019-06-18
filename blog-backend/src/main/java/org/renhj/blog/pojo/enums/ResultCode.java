package org.renhj.blog.pojo.enums;

public enum  ResultCode {
    OK(200, "ok"),
    SIGN_ERROR(120, "签名错误"),
    TIME_OUT(130, "访问超时"),
    BAD_REQUEST(400, "参数不正确"),
    INVALID_TOKEN(401, "TOKEN无效"),
    UNLOGIN(401, "未登录"),
    INVALID_PASSWORD(402, "密码不正确"),
    FORBIDDEN(403, "无此权限"),
    METHOD_NOT_SUPPORT(405, "不支持该请求方法"),
    SYSTEM_ERROR(500, "服务器运行异常"),
    NOT_EXIST_USER_OR_PWD_ERROR(10000, "用户不存在或密码错误"),
    LOGIN_IN(10001, "该用户已登录"),
    NOT_EXIST_USER(10002, "该用户不存在"),
    UNAUTH_ERROR(10003, "未授权"),
    BIND_PHONE(10004, "请绑定手机号"),
    INVALID_CAPTCHA(10005, "无效的验证码"),
    TOKEN_EXPIRE(10006, "token已过期"),
    CAPTCHA_EXPIRE(10007, "验证码已过期"),
    USER_FROZEN(10008, "该用户已被锁定"),
    KICKOUT_USER(10009, "该账号已在其他地方登录！"),
    UPLOAD_ERROR(20000, "上传文件错误"),
    UPLOAD_FILE_FORMAT_ERROR(20001, "上传文件格式不正确"),
    CAPATCH_ERROR(30001, "人机验证接口错误");


    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
