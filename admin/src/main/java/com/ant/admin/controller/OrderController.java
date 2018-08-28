package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.entity.Order;
import com.ant.admin.service.OrderService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/15 11:54
 */
@RestController
@RequestMapping("/order")
public class OrderController extends  AbstractController{

    @Autowired
    private OrderService orderService;

    /**
     * 列表
     * 产品订单
     * @param params
     * @param order
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:cloud:list")
    public Result list(@RequestParam Map<String,Object> params, Order order){
        EntityWrapper<Order> wrapper = new EntityWrapper<Order>();
        wrapper.like("categroy.categroy_name", order.getCategoryName());
        PageUtils page = new PageUtils(orderService.queryPage(params, wrapper));
        return Result.ok().put("page", page);
    }
}
