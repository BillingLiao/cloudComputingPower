package com.ant.admin.controller;

import com.ant.admin.common.utils.Result;
import com.ant.admin.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Billing
 * @date 2018/8/15 11:59
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    @ResponseBody
    public Result login(User user){
        logger.info("登陆请求参数：{}", user.toString());

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken utoken = new UsernamePasswordToken(user.getUserName(),user.getPassword());
        //utoken.setRememberMe(user.isRemeberMe());//设置是否记住登陆状态
        //如果用户登陆成功则直接返回Result，如果登陆失败则会打印异常信息，异常信息需要手动捕捉
        //登陆的时候shiro会在自定义的AuthRealm中的doGetAuthenticationInfo方法中对用户信息进行验证
        //如果在接口上需要验证权限信息，shrio是在AuthRealm中的doGetAuthorizationInfo方法中验证当前用户是否具有访问权限
        subject.login(utoken);
        return Result.ok();
    }
}
