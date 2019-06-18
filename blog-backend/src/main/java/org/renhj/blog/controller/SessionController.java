package org.renhj.blog.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.renhj.blog.pojo.dto.Result;
import org.renhj.blog.pojo.enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionDAO sessionDAO;

    @GetMapping
    public Result getAll() {
        return Result.ok(sessionDAO.getActiveSessions());
    }

    @GetMapping("/current")
    public Result getSession() {
        return Result.ok(SecurityUtils.getSubject().getSession().getId());
    }

    @DeleteMapping("/{id}")
    public Result deleteSession(@PathVariable String id) {
        Session session = sessionDAO.readSession(id);
        sessionDAO.delete(session);
        return new Result(ResultCode.OK);
    }
}
