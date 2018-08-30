package com.ant.admin.service.impl;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.OrderDao;
import com.ant.admin.dao.ProductDao;
import com.ant.admin.entity.Order;
import com.ant.admin.entity.Product;
import com.ant.admin.entity.User;
import com.ant.admin.service.OrderService;
import com.ant.admin.service.ProductService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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

    /**
     * 下单
     * @param user
     * @param productId
     * @param amount
     */
    @Override
    public void add(User user, Integer productId, BigDecimal amount) {
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMddhhmmss");
        Product product = productDao.selectById(productId);
        Integer categoryId =  product.getCategoryId();
        String orderNo;
        if(categoryId == 1){
            orderNo = "KJ" + s.format(new Date());  //生成订单号
        }else if(categoryId == 2){
            orderNo = "YSL"+ s.format(new Date());
        }else if(categoryId == 3){
            orderNo = "LC" + s.format(new Date());
        }else{
            orderNo = "-";
        }
        Integer userId = user.getUserId();
        Date createTime = new Date();
        Order order = new Order(orderNo, productId, userId, 0, amount,createTime, 0);
        orderDao.insert(order);
    }
}
