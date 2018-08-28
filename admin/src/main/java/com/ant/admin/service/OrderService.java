package com.ant.admin.service;

import com.ant.admin.entity.Order;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:30
 */
public interface OrderService extends IService<Order> {

    Page<Order> queryPage(Map<String,Object> params, Wrapper<Order> wrapper);

}
