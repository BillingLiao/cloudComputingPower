package com.ant.admin.entity;

import com.ant.admin.common.validator.group.AddGroup;
import com.ant.admin.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
     * 盐
     */
    private String salt;

    /**
     * 手机号码
     */
    private String telphone;

    /**
     * 邮箱
     */
    @NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;

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
     * 角色ID列表
     */
    @TableField(exist=false)
    private List<Integer> roleIdList;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 注册时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date registerTime;

    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", telphone='" + telphone + '\'' +
                ", email='" + email + '\'' +
                ", btc_addr='" + btc_addr + '\'' +
                ", Alipay='" + Alipay + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                ", roleIdList=" + roleIdList +
                ", status=" + status +
                ", registerTime=" + registerTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
