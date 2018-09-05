package com.ant.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现记录表
 * @author Billing
 * @date 2018/9/5 18:05
 */
public class putForward implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
    private Integer putForwardId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 比特币(云算力余额)
     */
    private BigDecimal btc;

    /**
     * 人民币(理财余额)
     */
    private BigDecimal cny;

    /**
     * 提现日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date createTime;

    public Integer getPutForwardId() {
        return putForwardId;
    }

    public void setPutForwardId(Integer putForwardId) {
        this.putForwardId = putForwardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getBtc() {
        return btc;
    }

    public void setBtc(BigDecimal btc) {
        this.btc = btc;
    }

    public BigDecimal getCny() {
        return cny;
    }

    public void setCny(BigDecimal cny) {
        this.cny = cny;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
