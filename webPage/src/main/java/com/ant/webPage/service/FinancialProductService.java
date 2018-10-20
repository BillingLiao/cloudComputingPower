package com.ant.webPage.service;

import com.ant.entity.phone.FinancialProduct;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:32
 */
public interface FinancialProductService extends IService<FinancialProduct> {

    public List<FinancialProduct> findList();

    public FinancialProduct findOne(Integer productId);

    public List<FinancialProduct> findFour();
}
