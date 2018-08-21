package com.ant.admin.service;

import com.ant.admin.dto.FinancialProduct;
import com.ant.admin.entity.Product;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:32
 */
public interface FinancialProductService extends IService<FinancialProduct> {

    FinancialProduct infoFinancial(Integer productId);

    void insertProduct(FinancialProduct financialProduct);

    Page<FinancialProduct> queryPage(Map<String,Object> params, EntityWrapper<Product> wrapper);
}
