package com.ant.webPage.controller;

import com.ant.entity.Product;
import com.ant.entity.User;
import com.ant.webPage.service.OrderService;
import com.ant.webPage.service.ProductService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 订单controller
 * @author Billing
 * @date 2018/9/11 20:24
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController{

    @Autowired
    private OrderService orderService;

    @Autowired private ProductService productService;

    /**
     *  用户下单
     * @param user  用户
     * @param productId 产品
     * @param amount    购买数量
     * @param actualReceipts  实收款
     * @param maturityIncome 到期收益
     * @return
     */
    @RequestMapping("/add")
    public Result add(@SessionAttribute User user, @RequestParam Integer productId, @RequestParam BigDecimal actualReceipts,
                      @RequestParam(required = false) BigDecimal amount, @RequestParam(required = false) BigDecimal maturityIncome){
        Product product = productService.selectById(productId);
        Integer type = product.getCategoryId();
        if(type == 2 || type == 1){ //云算力产品
            return orderService.addCloudOrder(user,product,amount,actualReceipts);
        }else if(type == 3) { //理财产品
            return orderService.addFinancialOrder(user, product, actualReceipts, maturityIncome);
        }
        return Result.ok();
    }
}
