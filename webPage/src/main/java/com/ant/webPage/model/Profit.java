package com.ant.webPage.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 计算利息
 * @author Billing
 * @date 2018/9/14 9:59
 */
public class Profit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 每日收益
     */
    private BigDecimal daily;

    /**
     * 每月收益
     */
    private BigDecimal monthly;

    /**
     * 到期收益
     */
    private BigDecimal maturityIncome;

    public Profit() {
    }

}
