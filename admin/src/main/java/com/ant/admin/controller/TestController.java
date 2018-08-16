package com.ant.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Billing
 * @date 2018/8/16 15:16
 */
@Controller
public class TestController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }


    @RequestMapping("/menu")
    public String menu() {
        return "menu";
    }

    @RequestMapping("/user")
    public String user() {
        return "user";
    }
}
