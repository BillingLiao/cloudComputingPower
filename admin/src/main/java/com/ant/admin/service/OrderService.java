package com.ant.admin.service;

import com.ant.admin.common.utils.PageUtils;
import com.ant.entity.Order;
import com.ant.entity.User;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:30
 */
public interface OrderService extends IService<Order> {

    PageUtils queryPage(Map<String, Object> params);

    Page<Order> queryPage(Map<String,Object> params, Wrapper<Order> wrapper);

    void add(User user, Integer productId, BigDecimal amount, BigDecimal actualReceipts);
}
