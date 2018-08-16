package com.ant.admin.service.impl;

import com.ant.admin.dao.OrderDao;
import com.ant.admin.entity.Order;
import com.ant.admin.service.OrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {

}
