package com.ant.admin.service.impl;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.OrderDao;
import com.ant.admin.entity.Order;
import com.ant.admin.service.OrderService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String categoryName=(String)params.get("categoryName");
        String orderStatus=(String)params.get("orderStatus");
        EntityWrapper<Order> orderEntityWrapper =new EntityWrapper<Order>();

        orderEntityWrapper.like("c.category_name", categoryName);
        orderEntityWrapper.eq(StringUtils.isNotBlank(orderStatus), "o.order_status", orderStatus);
        Page<Order> page =new Query<Order>(params).getPage();
        page.setRecords(baseMapper.selectOrderList(page,orderEntityWrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public Page<Order> queryPage(Map<String, Object> params, Wrapper<Order> wrapper) {
        Page<Order> page =new Query<Order>(params).getPage();
        return page.setRecords(baseMapper.selectOrderList(page,wrapper));
    }
}
