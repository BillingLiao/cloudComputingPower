package com.ant.admin.common.quartz;

import com.ant.admin.service.IncomeService;
import com.ant.admin.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时任务
 *
 * @author Billing
 * @date 2018/8/30 16:45
 */
@Component
public class CalculateDailyIncomeJob {

    @Resource
    private IncomeService incomeService; // 订单收益Service

    @Resource
    private OrderService orderService; // 订单Service

    /**
     * 每天凌晨2点定期执行
     */
    @Scheduled(cron="0 0 2 * * ?")
    public void work(){
        /**
         * 计算购买产品每日收益，全部插入收益表
         * 将云算力产品每日收益插入用户余额
         */
        incomeService.insertAllIncome();
    }

    /**
     * 每日凌晨2点01分定期执行
     */
    @Scheduled(cron="1 0 2 * * ?")
    public void work2(){
        /**
         *  判断理财产品是否到期
         *  到期后将本金收益插入用户余额更改订单状态
         */
        orderService.updateTypeByTime();
    }

}
