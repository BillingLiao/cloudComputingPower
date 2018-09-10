package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.common.validator.ValidatorUtils;
import com.ant.entity.Order;
import com.ant.entity.User;
import com.ant.admin.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 订单controller
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
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:list")
    public Result list(@RequestParam Map<String,Object> params){
        PageUtils page=orderService.queryPage(params);
/*        EntityWrapper<Order> wrapper = new EntityWrapper<Order>();
        wrapper.like("c.category_name", order.getCategoryName());
        PageUtils page = new PageUtils(orderService.queryPage(params, wrapper));*/
        return Result.ok().put("page", page);
    }

    /**
     * 保存
     */
    @RequestMapping("/status")
    @RequiresPermissions("order:status:update")
    public Result update(@RequestBody Order order) throws ParseException {
        //校验类型
        ValidatorUtils.validateEntity(order);
        if(order.getOrderStatus() == 2){
            order.setPaymentTime(new Date());
        }
        orderService.updateAllColumnById(order);
        return Result.ok("更新成功");
    }



    /**
     *  用户下单
     *
     * @param user  用户
     * @param productId 产品
     * @param amount    购买数量
     * @param actualReceipts  实收款
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
