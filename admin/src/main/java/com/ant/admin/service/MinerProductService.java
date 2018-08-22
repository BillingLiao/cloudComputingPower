package com.ant.admin.service;

import com.ant.admin.dto.MinerProduct;
import com.ant.admin.entity.Product;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:32
 */
public interface MinerProductService extends IService<MinerProduct> {

    MinerProduct infoMiner(Integer productId) throws Exception;

    void insertMiner(MinerProduct minerProduct);

    Page<MinerProduct> queryPage(Map<String,Object> params, Wrapper<Product> wrapper);
}