package com.madd.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String login(){
        System.err.println("进入登录页面---login");
        return "user/login";
    }
    @RequestMapping("/forget")
    public String forget(){
        System.err.println("进入忘记密码页面---forget");
        return "user/forget";
    }
    @RequestMapping("/reg")
    public String register(){
        System.err.println("进入注册页面---register");
        return "user/reg";
    }
    @RequestMapping("/index")
    public String index(){
        System.err.println("进入主页面---index");
        return "index";
    }
}
