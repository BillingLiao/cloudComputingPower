package com.ant.admin.common.quartz;

import com.ant.admin.service.IncomeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 计算每日收益 定时任务
 *
 * @author Billing
 * @date 2018/8/30 16:45
 */
@Component
public class CalculateDailyIncomeJob {

    @Resource
    private IncomeService incomeService; // 订单收益Service

    /**
     * 每天凌晨2点定期执行
     */
    @Scheduled(cron="0 0 2 * * ?")
    public void work(){
        incomeService.insertAllIncome();
    }
}
