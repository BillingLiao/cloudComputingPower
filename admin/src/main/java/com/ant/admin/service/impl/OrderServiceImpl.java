package com.ant.admin.service.impl;

import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.OrderDao;
import com.ant.admin.entity.Order;
import com.ant.admin.service.OrderService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {

    @Override
    public Page<Order> queryPage(Map<String, Object> params, Wrapper<Order> wrapper) {
        Page<Order> page =new Query<Order>(params).getPage();
        return page.setRecords(baseMapper.selectOrderList(page,wrapper));
    }
}
