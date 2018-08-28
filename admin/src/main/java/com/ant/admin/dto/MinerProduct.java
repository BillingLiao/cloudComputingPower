package com.ant.admin.dto;

import com.ant.admin.entity.Product;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 矿机产品明细表
 *
 * @author Billing
 * @date 2018/8/13 11:21
 */
@TableName("t_miner_product")
public class MinerProduct extends Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 矿机明细id
     */
    @TableId
    private Integer minerId;

    /**
     * 产品Id
     */
    private Integer productId;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 额定算力
     */
    private BigDecimal stock;

    /**
     * 机型
     */
    private String model;

    /**
     * 参考收益
     */
    private String remark;

    /**
     * 电源
     */
    private String power;

    /**
     * 电费
     */
    private BigDecimal electricityFees;

    /**
     * 管理费
     */
    private BigDecimal managementExpense;


    /**
     * 假删除 0：未删除 1：删除
     */
    private Integer delFlag;

    /**
     * 分类名称
     */
    @TableField(exist = false)
    private String categoryName;

    /**
     * 产品类别编号
     */
    @TableField(exist = false)
    private Integer categoryId;

    /**
     * 产品名称
     */
    @TableField(exist = false)
    private String productName;

    /**
     * 产品介绍
     */
    @TableField(exist = false)
    private String introduction;


    /**
     * 是否上架：1=上架/0=下架
     */
    @TableField(exist = false)
    private Integer showInShelve;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(exist = false)
    private Date createAt;

    /**
     * 更新时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(exist = false)
    private Date updateAt;

    /**
     * 创建者
     */
    @TableField(exist = false)
    private Integer createUser;

    /**
     * 更新者
     */
    @TableField(exist = false)
    private Integer updateUser;

    public Integer getMinerId() {
        return minerId;
    }

    public void setMinerId(Integer minerId) {
        this.minerId = minerId;
    }

    @Override
    public Integer getProductId() {
        return productId;
    }

    @Override
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public BigDecimal getElectricityFees() {
        return electricityFees;
    }

    public void setElectricityFees(BigDecimal electricityFees) {
        this.electricityFees = electricityFees;
    }

    public BigDecimal getManagementExpense() {
        return managementExpense;
    }

    public void setManagementExpense(BigDecimal managementExpense) {
        this.managementExpense = managementExpense;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
