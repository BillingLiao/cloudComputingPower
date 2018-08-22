package com.ant.admin.dao;

import com.ant.admin.dto.CloudProduct;
import com.ant.admin.entity.Product;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * 云算力明细
 *
 * @author Billing
 * @date 2018/8/20 19:19
 */
public interface CloudProductDao extends BaseMapper<CloudProduct> {

    List<CloudProduct> selectProductList(Page<CloudProduct> page, Wrapper<Product> wrapper);
}
