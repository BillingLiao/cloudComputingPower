package com.ant.webPage.service;

import com.ant.entity.Order;
import com.ant.entity.Product;
import com.ant.entity.User;
import com.ant.webPage.util.Result;
import com.baomidou.mybatisplus.service.IService;

import java.math.BigDecimal;

/**
 * 订单service
 * @author Billing
 * @date 2018/9/5 14:55
 */
public interface OrderService extends IService<Order> {

    Result addCloudOrder(User user, Product product, BigDecimal amount, BigDecimal actualReceipts);

    Result addFinancialOrder(User user, Product product, BigDecimal actualReceipts, BigDecimal maturityIncome);
}
