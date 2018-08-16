package com.ant.admin.entity;

import java.io.Serializable;

/**
 * 产品类别表
 *
 * @author Billing
 * @date 2018/8/10 18:02
 */
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品编号
     */
    private Integer categoryId;

    /**
     * 产品类别名称
     */
    private String categoryName;

    /**
     * 产品编码
     */
    private String categoryCode;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
