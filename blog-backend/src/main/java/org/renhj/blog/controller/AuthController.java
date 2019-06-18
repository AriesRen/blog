package org.renhj.blog.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.renhj.blog.pojo.dto.Result;
import org.renhj.blog.pojo.enums.LoginType;
import org.renhj.blog.pojo.enums.ResultCode;
import org.renhj.blog.pojo.vo.LoginVo;
import org.renhj.blog.pojo.vo.RegisterVo;
import org.renhj.blog.service.AuthService;
import org.renhj.blog.shiro.CustomLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
        CustomLoginToken token = new CustomLoginToken(LoginType.USER_PASSWORD, loginVo.getUsername(), loginVo.getPassword());
        return shiroLogin(token);
    }
    @PostMapping("/phoneLogin")
    public Result phoneLogin(@RequestBody LoginVo loginVo,HttpServletRequest request, HttpServletResponse response){
        CustomLoginToken token = new CustomLoginToken(LoginType.USER_PHONE, loginVo.getUsername(), loginVo.getPassword());
        return shiroLogin(token);
    }

    private Result shiroLogin(CustomLoginToken token) {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            if (subject.isAuthenticated()) {
                JSONObject json = new JSONObject();
                json.put("token", subject.getSession().getId());
                return Result.ok(json);
            }else {
                return new Result(ResultCode.NOT_EXIST_USER_OR_PWD_ERROR);
            }
        }catch (IncorrectCredentialsException | UnknownAccountException e) {
            e.printStackTrace();
            return new Result(ResultCode.NOT_EXIST_USER_OR_PWD_ERROR);
        } catch (DisabledAccountException e) {
            e.printStackTrace();
            return new Result(ResultCode.USER_FROZEN);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.SYSTEM_ERROR);
        }
    }

    @GetMapping("/unauth")
    public Result unAuth(){
        return new Result(ResultCode.UNAUTH_ERROR);
    }
    @GetMapping("/kickout")
    public Result kickout() {
        return new Result(ResultCode.KICKOUT_USER);
    }
    @GetMapping("/unlogin")
    public Result unLogin() {
        return new Result(ResultCode.UNLOGIN);
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo, HttpServletRequest request, HttpServletResponse response){
        return Result.ok(authService.register(registerVo));
    }

    @GetMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            subject.logout();
            return Result.ok();
        } else {
            return new Result(ResultCode.UNLOGIN);
        }
    }

}
