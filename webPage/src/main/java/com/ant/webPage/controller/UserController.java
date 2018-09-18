package com.ant.webPage.controller;

import com.ant.entity.User;
import com.ant.webPage.model.Account;
import com.ant.webPage.model.UserFinancial;
import com.ant.webPage.service.IncomeService;
import com.ant.webPage.service.OrderService;
import com.ant.webPage.service.UserService;
import com.ant.webPage.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/9/5 15:09
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    /**
     *  查询用户账户信息
     * @param user
     * @return
     */
    @RequestMapping("/my")
    public Result my(@SessionAttribute User user){
        Integer userId = user.getUserId();
        Account account = userService.selectAccountByUserId(userId);
        return Result.ok().put("account",account);
    }

    @RequestMapping("/detailed")
    private Result detailed(@SessionAttribute User user){
        Integer userId = user.getUserId();
        Account account = userService.selectAccountByUserId(userId);
        UserFinancial userFinancial = orderService.selectUserFinancial(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("account",account);
        result.put("userFinancial",userFinancial);
        return Result.ok().put("result",result);
    }


}
