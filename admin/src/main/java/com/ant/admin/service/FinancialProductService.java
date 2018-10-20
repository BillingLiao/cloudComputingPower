package com.ant.admin.service;

import com.ant.entity.phone.FinancialProduct;
import com.ant.entity.phone.Product;
import com.baomidou.mybatisplus.mapper.Wrapper;
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

    void deleteProduct(Integer[] productIds);

    Page<FinancialProduct> queryPage(Map<String,Object> params, Wrapper<Product> wrapper);


    void updateProduct(FinancialProduct financialProduct);
}
