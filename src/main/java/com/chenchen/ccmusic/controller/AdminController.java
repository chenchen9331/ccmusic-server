package com.chenchen.ccmusic.controller;

import com.chenchen.ccmusic.common.entity.ResultEntity;
import com.chenchen.ccmusic.common.entity.StatusCode;
import com.chenchen.ccmusic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 管理员Controller
 * @author chenchen
 */
@RestController
public class AdminController {

    /**
     * AdminService注入
     */
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin/login/status", method = RequestMethod.POST)
    public ResultEntity login(HttpServletRequest request, HttpSession session) {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        boolean flag = adminService.login(name, password);
        if (flag) {
            return new ResultEntity(StatusCode.OK, true, "登录成功");
        }
        return new ResultEntity(StatusCode.LOGINERROR, false, "用户名或密码错误");
    }


}
