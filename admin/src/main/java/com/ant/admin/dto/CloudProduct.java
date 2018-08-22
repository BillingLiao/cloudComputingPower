package com.ant.admin.dto;

import com.ant.admin.entity.Product;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 云算力产品明细表
 *
 * @author Billing
 * @date 2018/8/13 11:05
 */
@TableName("t_cloud_product")
public class CloudProduct extends Product implements Serializable {

    private static final long serialVersionUID = 1L;

    public CloudProduct(){
    }

    /**
     * 理财明细id
     */
    @TableId
    private Integer cloudId;

    /**
     * 产品Id
     */
    private Integer productId;


    /**
     * 本期总算力
     */
    private BigDecimal totalStock;

    /**
     * 剩余算力
     */
    private BigDecimal stock;

    /**
     * 电费
     */
    private BigDecimal ElectricityFees;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 本期售卖时间
     */
    private String saleTime;

    /**
     * 机型
     */
    private String model;

    /**
     * 额定算力
     */
    private String rated;

    /**
     * 参考收益
     */
    private String remark;

    /**
     * 起投单位/T
     */
    private Integer startStep;

    /**
     * 电源
     */
    private String power;

    /**
     * 管理费
     */
    private BigDecimal managementExpense;

    /**
     * 假删除 0：未删除 1：删除
     */
    private Integer delFlag;

    public Integer getCloudId() {
        return cloudId;
    }

    public void setCloudId(Integer cloudId) {
        this.cloudId = cloudId;
    }

    @Override
    public Integer getProductId() {
        return productId;
    }

    @Override
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(BigDecimal totalStock) {
        this.totalStock = totalStock;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public BigDecimal getElectricityFees() {
        return ElectricityFees;
    }

    public void setElectricityFees(BigDecimal electricityFees) {
        ElectricityFees = electricityFees;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStartStep() {
        return startStep;
    }

    public void setStartStep(Integer startStep) {
        this.startStep = startStep;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public BigDecimal getManagementExpense() {
        return managementExpense;
    }

    public void setManagementExpense(BigDecimal managementExpense) {
        this.managementExpense = managementExpense;
    }

    @Override
    public Integer getDelFlag() {
        return delFlag;
    }

    @Override
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
