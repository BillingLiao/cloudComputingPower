package com.ant.webPage.controller.PC;


import com.ant.webPage.service.UserService;
import com.ant.webPage.util.MMP;
import com.ant.webPage.util.RedisUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@CrossOrigin(allowCredentials = "true")
@RestController
public class PCLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/login")
    public PrInfo Login(){
        return MMP.Error(401,"不能使用这种方式登录！",null);
    }


    @PostMapping("/login")
    public Object Login(HttpServletRequest request, HttpServletResponse response, @RequestParam String phone, @RequestParam String pass){
        HttpSession session = request.getSession();
        //redisUtils.set("CLOUD_"+session.getId(),userService.login(phone,pass),86400000);
        return userService.login(session,phone,pass);
    }
}
