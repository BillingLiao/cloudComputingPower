package com.ant.admin.dao;

import com.ant.admin.dto.MinerProduct;
import com.ant.admin.entity.Product;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * 矿机
 *
 * @author Billing
 * @date 2018/8/20 19:19
 */
public interface MinerProductDao extends BaseMapper<MinerProduct> {

    /**
     * 分页查询矿机产品
     * @param page
     * @param wrapper
     * @return
     */
    List<MinerProduct> selectProductList(Page<MinerProduct> page, Wrapper<Product> wrapper);

    /**
     * 根据产品id查询矿机产品
     * @param productId
     * @return
     */
    MinerProduct selectByProductId(Integer productId);
}
