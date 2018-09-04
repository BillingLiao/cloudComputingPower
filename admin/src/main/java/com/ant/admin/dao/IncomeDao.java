package com.ant.admin.dao;

import com.ant.entity.Income;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * @author Billing
 * @date 2018/8/13 19:20
 */
public interface IncomeDao extends BaseMapper<Income> {

    /**
     * 插入理财产品收益
     */
    void insertFinancialInomeList();

    /**
     * 插入云算力产品收益
     */
    void insertCloudInomeList();
}
