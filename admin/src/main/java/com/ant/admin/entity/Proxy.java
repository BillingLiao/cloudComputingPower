package com.ant.admin.entity;

import java.io.Serializable;

/**
 * 代理表
 *
 * @author Billing
 * @date 2018/8/13 9:36
 */
public class Proxy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 代理编号
     */
    private Integer proxyId;

    /**
     * 代理人编号
     */
    private Integer userId;

    /**
     * 被代理人编号
     */
    private Integer subordinateId;

    /**
     * 代理等级
     */
    private Integer level;

    public Integer getProxyId() {
        return proxyId;
    }

    public void setProxyId(Integer proxyId) {
        this.proxyId = proxyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSubordinateId() {
        return subordinateId;
    }

    public void setSubordinateId(Integer subordinateId) {
        this.subordinateId = subordinateId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
