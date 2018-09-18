package com.ant.webPage.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 个人账户信息
 * @author Billing
 * @date 2018/9/14 9:59
 */
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 持有算力
     */
    private BigDecimal amount;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 余额(比特币)
     */
    private BigDecimal btcBalance;

    /**
     * 余额（人民币）
     */
    private BigDecimal cnyBalance;

    /**
     * 昨日收益(btc)
     */
    private BigDecimal btcYesterday;

    /**
     * 昨日收益(cny)
     */
    private BigDecimal cnyYesterday;

    public Account() {
    }

    public Account(BigDecimal amount,String phone, BigDecimal btcBalance, BigDecimal cnyBalance, BigDecimal btcYesterday, BigDecimal cnyYesterday) {
        this.amount = amount;
        this.phone = phone;
        this.btcBalance = btcBalance;
        this.cnyBalance = cnyBalance;
        this.btcYesterday = btcYesterday;
        this.cnyYesterday = cnyYesterday;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getBtcBalance() {
        return btcBalance;
    }

    public void setBtcBalance(BigDecimal btcBalance) {
        this.btcBalance = btcBalance;
    }

    public BigDecimal getCnyBalance() {
        return cnyBalance;
    }

    public void setCnyBalance(BigDecimal cnyBalance) {
        this.cnyBalance = cnyBalance;
    }

    public BigDecimal getBtcYesterday() {
        return btcYesterday;
    }

    public void setBtcYesterday(BigDecimal btcYesterday) {
        this.btcYesterday = btcYesterday;
    }

    public BigDecimal getCnyYesterday() {
        return cnyYesterday;
    }

    public void setCnyYesterday(BigDecimal cnyYesterday) {
        this.cnyYesterday = cnyYesterday;
    }
}
