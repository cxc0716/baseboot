package com.cxc.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cxc.common.util.PasswordEncoder;
import com.cxc.domain.HiUser;
import com.cxc.service.UserService;
import com.cxc.vo.AjaxResult;
import com.cxc.vo.UserSimpleInfo;

/**
 * author:chenxinchao date:2016-09-22 13:06 desc:com.cxc.controller
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request,
        HttpServletResponse response) {
        return "main";
    }

    @RequestMapping("/")
    public String defaultPage(HttpServletRequest request,
        HttpServletResponse response) {
        return "forward:/login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public AjaxResult doLogin(HiUser user, HttpServletRequest request,
        HttpServletResponse response, BindingResult bindingResult) {
        return initSuccessResult();
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request,
        HttpServletResponse response) {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,
        HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        return "login";
    }
}
