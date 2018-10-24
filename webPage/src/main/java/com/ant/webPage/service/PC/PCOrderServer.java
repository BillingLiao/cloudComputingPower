package com.ant.webPage.service.PC;

import com.ant.entity.PC.PCOrder;
import com.ant.entity.phone.Order;
import com.ant.webPage.dao.OrderDao;
import com.ant.webPage.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PCOrderServer {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDao orderDao;

    public Object OrderAll(Integer orderId){
        try {
            if (orderId == null)
                return "总得告诉我要查谁的吧！";
            List<Order> orders = orderDao.selectCloudAll(Integer.valueOf(orderId));
            List<PCOrder> pcOrders = new ArrayList<>();
            for (Order order : orders){
                PCOrder order1 = new PCOrder();
                order1.setId(order.getOrderId());
                order1.setOrderId(order.getOrderNo());
                order1.setOrderCommodityId(""+order.getProductId());
                order1.setOrderCommodityName(""+order.getCategoryName());
                order1.setOrderCommodityType(""+order.getOrderType());
                //////////////////////////////////////订单名称，暂时留空
                order1.setOrderName("");
                //////////////////////////////////////
                order1.setOrderMoney(""+order.getActualReceipts());
                order1.setOrderNum(""+order.getAmount());
                ///////////////////期限（暂未实现）/////////////////////////////
                order1.setOrderTerm("0");
                order1.setOrderStartTime("");
                order1.setOrderStopTime("");
                //////////////////////////////////////////////////////////////
                order1.setOrderState(""+order.getOrderStatus());
                //根据order.getUserId()获取到值去查询（暂未实现）//////////////////
                order1.setName("");
                order1.setEmail("");
                order1.setPhone("");
                ////////////////////////////////////////////////////////////////
                order1.setOrderTime(order.getCreateTime());
                pcOrders.add(order1);
            }
            return pcOrders;
        }catch (Exception e){
            log.error("查询全部订单错误");
            log.error(""+e);
            return "给我了一些奇怪的东西！";
        }
    }


    public Object OrderOne(Integer userId,String orderId){
        try {
            Order order = orderDao.selectById(Integer.valueOf(orderId));
            PCOrder order1 = new PCOrder();
            order1.setId(order.getOrderId());
            order1.setOrderId(order.getOrderNo());
            order1.setOrderCommodityId(""+order.getProductId());
            order1.setOrderCommodityName(""+order.getCategoryName());
            order1.setOrderCommodityType(""+order.getOrderType());
            //////////////////////////////////////订单名称，暂时留空
            order1.setOrderName("");
            //////////////////////////////////////
            order1.setOrderMoney(""+order.getActualReceipts());
            order1.setOrderNum(""+order.getAmount());
            ///////////////////期限（暂未实现）/////////////////////////////
            order1.setOrderTerm("0");
            order1.setOrderStartTime("");
            order1.setOrderStopTime("");
            //////////////////////////////////////////////////////////////
            order1.setOrderState(""+order.getOrderStatus());
            //根据order.getUserId()获取到值去查询（暂未实现）//////////////////
            order1.setName("");
            order1.setEmail("");
            order1.setPhone("");
            ////////////////////////////////////////////////////////////////
            order1.setOrderTime(order.getCreateTime());
            return order1;
        }catch (Exception e){
            return "好奇怪！你居然没有订单！";
        }
    }

}
