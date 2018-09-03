package com.ant.webPage.controller;

import com.ant.admin.common.utils.Result;
import com.ant.admin.controller.AbstractController;
import com.ant.admin.service.OrderService;
import com.ant.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigDecimal;

/**
 * @author Billing
 * @date 2018/8/15 11:54
 */
@RestController
@RequestMapping("/user/order")
public class OrderController extends AbstractController {

    @Autowired
    private OrderService orderService;

    /**
     *  用户下单
     *
     * @param user  用户
     * @param productId 产品
     * @param amount    购买数量
     * @param actualReceipts    实收款
     * @return
     */
    @RequestMapping("/add")
    public Result add(@SessionAttribute User user, @RequestParam Integer productId, @RequestParam(required = false) BigDecimal amount,@RequestParam BigDecimal actualReceipts){
        if(amount == null){
            amount = new BigDecimal("1");
        }
        orderService.add(user,productId,amount,actualReceipts);
        return Result.ok();
    }
}
