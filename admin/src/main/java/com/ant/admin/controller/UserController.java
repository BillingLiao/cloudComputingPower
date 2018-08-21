package com.ant.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Billing
 * @date 2018/8/15 11:59
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/login")
    public ModelAndView goToLoginPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
