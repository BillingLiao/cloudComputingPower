package com.ant.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 产品收益表
 *
 * @author Billing
 * @date 2018/8/10 18:08
 */
@TableName("t_income")
public class Income implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收益编号
     */
    @TableId
    private Integer incomeId;

    /**
     * 订单编号
     */
    private Integer orderId;

    /**
     * 收益类别 -根据订单的产品类别判断
     */
    private String incomeType;

    /**
     * 每日收益
     */
    private BigDecimal dailyIncome;

    /**
     * 总收益
     */
    private BigDecimal totalIncome;

    /**
     * 收益单位
     */
    private String unit;

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public BigDecimal getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(BigDecimal dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
