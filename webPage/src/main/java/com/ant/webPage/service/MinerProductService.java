package com.ant.webPage.service;

import com.ant.entity.phone.MinerProduct;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:32
 */
public interface MinerProductService extends IService<MinerProduct> {

    public List<MinerProduct> findList();

    public MinerProduct findOne(Integer productId);
}
