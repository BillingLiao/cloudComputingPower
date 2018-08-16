package com.ant.admin.dto;

import com.ant.admin.entity.Product;

import java.math.BigDecimal;

/**
 * 理财产品
 *
 * @author Billing
 * @date 2018/8/13 10:21
 */
public class FinancialProduct {

    /**
     * 产品
     */
    private Product product;

    /**
     * 起投金额
     */
    private BigDecimal thresholdAmount;

    /**
     * 投资步长
     */
    private BigDecimal stepTerm;

    /**
     * 锁定期
     */
    private Integer lockAmount;

    /**
     * 年化收益率
     */
    private BigDecimal rewardRate;


    /**
     * 销售状态
     */
    private String salesStatus;

    /**
     * 备注
     */
    private String memo;
}
