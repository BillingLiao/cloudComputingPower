package com.ant.admin.service;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.dto.CloudProduct;
import com.ant.admin.dto.FinancialProduct;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:32
 */
public interface CloudProductService extends IService<CloudProduct> {

    CloudProduct infoCloud(Integer productId);

    PageUtils ListCloud(Map<String,Object> params, CloudProduct cloudProduct);

    void insertCloud(CloudProduct cloudProduct);
}
