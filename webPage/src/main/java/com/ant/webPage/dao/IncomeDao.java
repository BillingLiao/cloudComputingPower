package com.ant.webPage.dao;

import com.ant.entity.Income;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:20
 */
public interface IncomeDao extends BaseMapper<Income> {

    /**
     * 查询出用户云算力产品昨日所有收益
     * @return
     */
    public BigDecimal selectCloudIncomeUser(Integer userId);

    /**
     * 查询用户理财产品昨日所有收益
     * @return
     */
    public BigDecimal selectFinancialIncomeUser(Integer userId);
}
