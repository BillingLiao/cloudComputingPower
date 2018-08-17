package com.ant.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * @author Billing
 * @date 2018/8/13 10:04
 */
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登陆密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String telphone;

    /**
     * BTC地址
     */
    private String btc_addr;

    /**
     * 支付宝账号
     */
    private String Alipay;

    /**
     * 邀请码
     */
    private String invitationCode;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 注册时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private String registerTime;

    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private String updateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getBtc_addr() {
        return btc_addr;
    }

    public void setBtc_addr(String btc_addr) {
        this.btc_addr = btc_addr;
    }

    public String getAlipay() {
        return Alipay;
    }

    public void setAlipay(String alipay) {
        Alipay = alipay;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
