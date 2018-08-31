package com.ant.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Billing
 * @date 2018/8/13 9:31
 */
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    public Order() {
    }

    public Order(String orderNo, Integer productId, Integer userId, Integer orderType,Integer orderStatus, BigDecimal amount,BigDecimal actualReceipts, Date createTime, Integer delFlag) {
        this.orderNo = orderNo;
        this.productId = productId;
        this.userId = userId;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.amount = amount;
        this.actualReceipts = actualReceipts;
        this.createTime = createTime;
        this.delFlag = delFlag;
    }

    /**
     * 订单编号
     */
    @TableId
    private Integer orderId;

    /**
     * 订单单号
     */
    private String orderNo;

    /**
     * 产品编号
     */
    private Integer productId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 订单类型  1:矿机产品 2:运算力产品 3:理财产品
     */
    private Integer orderType;

    /**
     * 订单状态 0:待支付 1:待支付关闭(取消订单) 2:已付款，待发货(产品开始计算收益)  3:待收货 4:已收货 5:已完成订单
     */
    private Integer orderStatus;

    /**
     * 购买数量
     */
    private BigDecimal amount;

    /**
     * 实收款
     */
    private BigDecimal actualReceipts;

    /**
     * 备注
     */
    private String memo;

    /**
     * 订单提交时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date createTime;

    /**
     * 付款时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date deliveryTime;

    /**
     * 发货时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date paymentTime;

    /**
     * 收货时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date receivingTime;

    /**
     * 假删除 0：未删除 1：删除
     */
    private Integer delFlag;

    /**
     * 分类名称
     */
    @TableField(exist = false)
    private String categoryName;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getActualReceipts() {
        return actualReceipts;
    }

    public void setActualReceipts(BigDecimal actualReceipts) {
        this.actualReceipts = actualReceipts;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Date receivingTime) {
        this.receivingTime = receivingTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
