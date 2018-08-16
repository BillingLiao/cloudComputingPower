package com.ant.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告表
 *
 * @author Billing
 * @date 2018-8-10 16:25
 */
public class Totices implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 公告编号
     */
    private Integer toticesId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date publishDate;

    /**
     * 更新日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**
     * 发布人
     */
    private Integer puglishUser;

    /**
     * 更新人
     */
    private Integer updateUser;

    public Integer getToticesId() {
        return toticesId;
    }

    public void setToticesId(Integer toticesId) {
        this.toticesId = toticesId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getPuglishUser() {
        return puglishUser;
    }

    public void setPuglishUser(Integer puglishUser) {
        this.puglishUser = puglishUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
}
