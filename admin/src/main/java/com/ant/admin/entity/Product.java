package com.ant.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Set;

/**
 * 产品表 ---矿机、云算力、理财
 *
 * @author Billing
 * @date 2018/8/10 18:25
 */
@TableName("t_product")
public class Product<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public Product(){

    }

    public Product(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 产品编号
     */
    @TableId
    private Integer productId;

    /**
     * 产品类别编号
     */
    private Integer categoryId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品介绍
     */
    private String introduction;

    /**
     * 产品属性集合
     */
    private Set<ProductAttributes> productAttributes;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(fill = FieldFill.INSERT)
    private Date createAt;

    /**
     * 更新时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(fill = FieldFill.UPDATE)
    private Date updateAt;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer createUser;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.UPDATE)
    private Integer updateUser;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Set<ProductAttributes> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(Set<ProductAttributes> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
}
