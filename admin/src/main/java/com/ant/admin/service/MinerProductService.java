package com.ant.admin.service;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.dto.FinancialProduct;
import com.ant.admin.dto.MinerProduct;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:32
 */
public interface MinerProductService extends IService<MinerProduct> {

    MinerProduct infoMiner(Integer productId) throws Exception;

    PageUtils ListMiner(Map<String,Object> params, MinerProduct minerProduct);

    void insertMiner(MinerProduct minerProduct);
}
