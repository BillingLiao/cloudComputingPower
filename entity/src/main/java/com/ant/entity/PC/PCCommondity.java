package com.ant.entity.PC;

import javax.persistence.*;

public class PCCommondity {

    private Integer id;

    private String commodityId;

    private String commodityName;

    private String commodityStock;

    private String commodityInitialStock;

    private String commodityMoney;

    private String commodityType;

    //交割时间，如果是0，则当天交割
    private String commodityTime;

    //期限，如果为0，则是永久商品，期限商品则是交割之日起开始计算。
    private String commodityTerm;

    //产品图片
    private String commodityUrl;

    //产品介绍
    private String commodityintroduction;

    private Integer HHHHH;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityStock() {
        return commodityStock;
    }

    public void setCommodityStock(String commodityStock) {
        this.commodityStock = commodityStock;
    }

    public String getCommodityInitialStock() {
        return commodityInitialStock;
    }

    public void setCommodityInitialStock(String commodityInitialStock) {
        this.commodityInitialStock = commodityInitialStock;
    }

    public String getCommodityMoney() {
        return commodityMoney;
    }

    public void setCommodityMoney(String commodityMoney) {
        this.commodityMoney = commodityMoney;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public String getCommodityTime() {
        return commodityTime;
    }

    public void setCommodityTime(String commodityTime) {
        this.commodityTime = commodityTime;
    }

    public String getCommodityTerm() {
        return commodityTerm;
    }

    public void setCommodityTerm(String commodityTerm) {
        this.commodityTerm = commodityTerm;
    }

    public String getCommodityUrl() {
        return commodityUrl;
    }

    public void setCommodityUrl(String commodityUrl) {
        this.commodityUrl = commodityUrl;
    }

    public String getCommodityintroduction() {
        return commodityintroduction;
    }

    public void setCommodityintroduction(String commodityintroduction) {
        this.commodityintroduction = commodityintroduction;
    }

    public Integer getHHHHH() {
        return HHHHH;
    }

    public void setHHHHH(Integer HHHHH) {
        this.HHHHH = HHHHH;
    }
}
