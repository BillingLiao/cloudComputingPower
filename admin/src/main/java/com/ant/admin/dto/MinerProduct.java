package com.ant.admin.dto;

import com.ant.admin.entity.Product;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 矿机产品明细表
 *
 * @author Billing
 * @date 2018/8/13 11:21
 */
@TableName("t_miner_product")
public class MinerProduct extends Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 矿机明细id
     */
    @TableId
    private Integer minerId;

    /**
     * 产品Id
     */
    private Integer productId;

    public Integer getMinerId() {
        return minerId;
    }

    public void setMinerId(Integer minerId) {
        this.minerId = minerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
