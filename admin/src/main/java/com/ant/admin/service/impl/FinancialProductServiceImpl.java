package com.ant.admin.service.impl;

import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.FinancialProductDao;
import com.ant.admin.dao.ProductDao;
import com.ant.admin.dto.FinancialProduct;
import com.ant.admin.entity.Product;
import com.ant.admin.service.FinancialProductService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 理财产品表
 *
 * @author Billing
 * @date 2018/8/13 19:40
 */
@Service("financialProductService")
public class FinancialProductServiceImpl extends ServiceImpl<FinancialProductDao,FinancialProduct> implements FinancialProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private FinancialProductDao financialProductDao;

    /**
     * 插入
     * @param productId
     * @return
     */
    @Override
    public FinancialProduct infoFinancial(Integer productId){
        FinancialProduct financialProduct = financialProductDao.selectById(productId);
        return financialProduct;
    }

    /**
     * 保存
     * @param financialProduct
     * @return
     */
    @Override
    public void insertProduct(FinancialProduct financialProduct){
        //插入产品表数据
        Product product = new Product(financialProduct);
        productDao.insert(product);
        financialProductDao.insert(financialProduct);
    }

    @Override
    public void deleteProduct(Integer[] productIds) {
        for(int i=0;i<productIds.length;i++){
            Integer productId=productIds[i];
            Product product = productDao.selectById(productId);
            FinancialProduct financialProduct = financialProductDao.selectById(productId);
            product.setDelFlag(1);
            financialProduct.setDelFlag(1);
            productDao.updateAllColumnById(product);
            financialProductDao.updateAllColumnById(financialProduct);
        }
    }

    @Override
    public Page<FinancialProduct> queryPage(Map<String, Object> params, Wrapper<Product> wrapper) {
        Page<FinancialProduct> page =new Query<FinancialProduct>(params).getPage();
        return page.setRecords(baseMapper.selectProductList(page,wrapper));
    }
}
