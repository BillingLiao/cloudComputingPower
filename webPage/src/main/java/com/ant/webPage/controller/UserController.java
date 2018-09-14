package com.ant.webPage.controller;

import com.ant.entity.User;
import com.ant.webPage.model.Account;
import com.ant.webPage.service.IncomeService;
import com.ant.webPage.service.OrderService;
import com.ant.webPage.service.UserService;
import com.ant.webPage.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
/**
 * @author Billing
 * @date 2018/9/5 15:09
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController{

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @RequestMapping("/my")
    public Result my(@SessionAttribute User user){
        Integer userId = user.getUserId();
        User selectUser = userService.selectById(userId);
        BigDecimal amount = orderService.selectAmountByUser(userId);
        BigDecimal btcBalance = selectUser.getBtc();
        BigDecimal cnyBalance = selectUser.getCny();
        String phone = selectUser.getTelphone();
        BigDecimal btcYesterday = incomeService.selectCloudIncomeUser(userId);
        BigDecimal cnyYesterday = incomeService.selectFinancialIncomeUser(userId);
        Account account = new Account(amount,phone,btcBalance,cnyBalance,btcYesterday,cnyYesterday);
        return Result.ok().put("account",account);
    }


}
