package com.ant.admin.service;

import com.ant.entity.Income;
import com.baomidou.mybatisplus.service.IService;

/**
 * 产品收益表
 *
 * @author Billing
 * @date 2018/8/13 19:28
 */
public interface IncomeService extends IService<Income> {

    /**
     * 插入所有订单每日收益
     */
    void insertAllIncome();
}
