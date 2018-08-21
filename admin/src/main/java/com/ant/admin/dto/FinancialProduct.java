package com.ant.admin.dto;

import com.ant.admin.entity.Product;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

/**
 * 理财产品明细表
 *
 * @author Billing
 * @date 2018/8/13 10:21
 */
@TableName("t_financial_product")
public class FinancialProduct extends Product implements Serializable {

    private static final long serialVersionUID = 1L;

    public FinancialProduct(){
    }

    /**
     * 理财明细id
     */
    @TableId
    private Integer financialId;

    /**
     * 产品Id
     */
    private Integer productId;

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

    //分类名称
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 销售状态
     */
    private Integer salesStatus;

    public Integer getFinancialId() {
        return financialId;
    }

    public void setFinancialId(Integer financialId) {
        this.financialId = financialId;
    }

    @Override
    public Integer getProductId() {
        return productId;
    }

    @Override
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getThresholdAmount() {
        return thresholdAmount;
    }

    public void setThresholdAmount(BigDecimal thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    public BigDecimal getStepTerm() {
        return stepTerm;
    }

    public void setStepTerm(BigDecimal stepTerm) {
        this.stepTerm = stepTerm;
    }

    public Integer getLockAmount() {
        return lockAmount;
    }

    public void setLockAmount(Integer lockAmount) {
        this.lockAmount = lockAmount;
    }

    public BigDecimal getRewardRate() {
        return rewardRate;
    }

    public void setRewardRate(BigDecimal rewardRate) {
        this.rewardRate = rewardRate;
    }

    public Integer getSalesStatus() {
        return salesStatus;
    }

    public void setSalesStatus(Integer salesStatus) {
        this.salesStatus = salesStatus;
    }

}
