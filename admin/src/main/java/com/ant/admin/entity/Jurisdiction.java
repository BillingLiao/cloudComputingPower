package com.ant.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 权限表
 *
 * @author Billing
 * @date 2018/8/10 18:19
 */
@TableName("t_jurisdiction")
public class Jurisdiction implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限编号
     */
    @TableId
    private Integer jurisdictionId;

    /**
     * 权限名称
     */
    private String jurisdictionName;


    /**
     * 权限url
     */
    private String url;

    /**
     * 权限等级
     */
    private Integer lever;

    /**
     * 权限图标
     */
    private String icon;

    /**
     * 父级id
     */
    private Integer parentId;

    /**
     * 是否开启
     */
    private Integer isOpen;

    public Integer getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(Integer jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

    public String getJurisdictionName() {
        return jurisdictionName;
    }

    public void setJurisdictionName(String jurisdictionName) {
        this.jurisdictionName = jurisdictionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLever() {
        return lever;
    }

    public void setLever(Integer lever) {
        this.lever = lever;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }
}
