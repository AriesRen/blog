package org.renhj.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.renhj.blog.config.GeetestConfig;
import org.renhj.blog.pojo.dto.Result;
import org.renhj.blog.sdk.GeetestLib;
import org.renhj.blog.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {


    @GetMapping
    public Result getCaptcha(HttpServletRequest request) {
        GeetestLib lib = new GeetestLib(GeetestConfig.getGeeTest_id(), GeetestConfig.getGeeTest_key(), GeetestConfig.isNewfangled());
        int status = lib.preProcess(getParams(request));
        HttpSession session = request.getSession();
        session.setAttribute(lib.gtServerStatusSessionKey, status);

        return Result.ok(JSONObject.parseObject(lib.getResponseStr()));
    }

    @PostMapping
    public Result checkCaptcha(HttpServletRequest request) {
        GeetestLib lib = new GeetestLib(GeetestConfig.getGeeTest_id(), GeetestConfig.getGeeTest_key(), GeetestConfig.isNewfangled());
        String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

        HttpSession session = request.getSession();
        int status = (int)(session.getAttribute(lib.gtServerStatusSessionKey));

        int result = 0;

        if (status == 1) {
            result = lib.enhencedValidateRequest(challenge, validate, seccode, getParams(request));
        } else {
            result = lib.failbackValidateRequest(challenge, validate, seccode);
        }
        return Result.ok(result != 0);
    }

    private HashMap<String, String> getParams(HttpServletRequest request) {
        HashMap<String, String> params = Maps.newHashMap();
        String ip = RequestUtils.getIPAddress(request);
        params.put("user_id", "renhj");
        params.put("client_type", "web");
        params.put("ip_address", "127.0.0.1");
        return params;
    }

}
