package com.ant.webPage.service.impl;

import com.ant.entity.*;
import com.ant.webPage.dao.*;
import com.ant.webPage.service.OrderService;
import com.ant.webPage.util.Result;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;
    @Autowired
    private MinerProductDao minerProductDao;
    @Autowired
    private CloudProductDao cloudProductDao;
    @Autowired
    private FinancialProductDao financialProductDao;



    /**
     * 添加云算力产品订单
     * @param user
     * @param product
     * @param amount
     * @param actualReceipts
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addCloudOrder(User user, Product product, BigDecimal amount, BigDecimal actualReceipts) {
        CloudProduct cloudProduct = cloudProductDao.selectByProductId(product.getProductId());
        BigDecimal stock = cloudProduct.getStock();
        int i = stock.compareTo(BigDecimal.ZERO);
        if(i == 0){ //判断有无库存
            return Result.error("产品已售完");
        }else{
            int num = stock.compareTo(amount);
            if(num == -1){
                return Result.error("不能大于库存");
            }
        }
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMddhhmmss");
        Integer categoryId =  product.getCategoryId();
        String orderNo;
        Integer orderType;
        if(categoryId == 1){
            orderType = 1;  //设置订单类型
            orderNo = "KJ" + s.format(new Date());  //生成订单号
        }else if(categoryId == 2){
            orderType = 2;
            orderNo = "YSL"+ s.format(new Date());
        }else{
            return Result.error();
        }
        Date createTime = new Date();
        Order order = new Order(orderNo, product.getProductId(), user.getUserId(), orderType,0, amount,actualReceipts,null,createTime, 0);
        orderDao.insert(order);
        return Result.ok("下单成功");
    }

    /**
     * 添加理财产品订单
     * @param user
     * @param product
     * @param actualReceipts
     * @param maturityIncome
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addFinancialOrder(User user, Product product, BigDecimal actualReceipts, BigDecimal maturityIncome) {
        BigDecimal money = new BigDecimal("100000000");
        int num = actualReceipts.compareTo(money);
        if(num == 1){
            return Result.error("请填入正确的金额");
        }
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMddhhmmss");
        Integer categoryId =  product.getCategoryId();
        String orderNo;
        Integer orderType;
        if(categoryId == 3){
            orderType = 3;
            orderNo = "LC" + s.format(new Date());
        }else{
            return Result.error();
        }
        Date createTime = new Date();
        Order order = new Order(orderNo, product.getProductId(), user.getUserId(), orderType,0, null,actualReceipts,maturityIncome,createTime, 0);
        orderDao.insert(order);
        return Result.ok("下单成功");
    }
}
