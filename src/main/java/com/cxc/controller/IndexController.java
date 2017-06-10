package com.cxc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author:chenxinchao date:2016-09-22 13:06 desc:com.cxc.controller
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request,
        HttpServletResponse response) {
        System.out.println("index");
        return "main";
    }

    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request,
        HttpServletResponse response) {
        System.out.println("doLogin");
        return "main";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request,
        HttpServletResponse response) {
        System.out.println("login");
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,
        HttpServletResponse response) {
        System.out.println("logout");
        return "login";
    }
}
