package com.ant.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Billing
 * @date 2018/8/10 18:40
 */
@TableName("t_product_attributes")
public class ProductAttributes implements Serializable {

    private static final long serialVersionUID = 1L;

    public ProductAttributes(){

    }

    public ProductAttributes(Integer productId, String remark, String name, String value, Short status, Integer sort) {
        this.productId = productId;
        this.remark = remark;
        this.name = name;
        this.value = value;
        this.status = status;
        this.sort = sort;
    }

    /**
     * 产品属性编号
     */
    @TableId
    private Integer attributesId;

    /**
     * 产品编号
     */
    private Integer productId;

    /**
     * 属性备注
     */
    private String remark;

    /**
     * 属性名
     */
    private String name;

    /**
     * 属性值
     */
    private String value;

    /**
     * 状态
     */
    private Short status;

    /**
     * 排序
     */
    private Integer sort;

    public Integer getAttributesId() {
        return attributesId;
    }

    public void setAttributesId(Integer attributesId) {
        this.attributesId = attributesId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
