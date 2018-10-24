package com.ant.entity.phone;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付单据表
 * @author Billing
 * @date 2018/10/22 15:31
 */
@TableName("t_pay")
public class Pay implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 支付单编号
     */
    @TableId
    private Integer payId;

    /**
     * 支付单单号
     */
    private String payNo;

    /**
     * 订单单号
     */
    private Integer orderId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 上传凭证图片
     */
    private String voucherUrl;

    /**
     * 开户行
     */
    private String openingBank;

    /**
     * 银行卡号
     */
    private String cardNumber;

    /**
     * 姓名
     */
    private String trueName;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(fill = FieldFill.UPDATE)
    private Date payTime;

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer usrId) {
        this.userId = usrId;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getVoucherUrl() {
        return voucherUrl;
    }

    public void setVoucherUrl(String voucherUrl) {
        this.voucherUrl = voucherUrl;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getOpeningBank() {
        return openingBank;
    }

    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }
}
