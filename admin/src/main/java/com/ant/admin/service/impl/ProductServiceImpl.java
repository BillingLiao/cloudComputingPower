package com.ant.admin.service.impl;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.dao.FinancialProductDao;
import com.ant.admin.dao.ProductDao;
import com.ant.admin.dto.FinancialProduct;
import com.ant.admin.entity.Product;
import com.ant.admin.entity.ProductAttributes;
import com.ant.admin.service.CategoryService;
import com.ant.admin.service.ProductAttributesService;
import com.ant.admin.service.ProductService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:38
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao,Product> implements ProductService {


    @Autowired
    private ProductDao productDao;

    @Autowired
    private FinancialProductDao financialProductDao;

    @Override
    public FinancialProduct infoFinancial(Integer productId) throws Exception {
        FinancialProduct financialProduct = financialProductDao.selectById(productId);
        return financialProduct;
    }

    @Override
    public PageUtils ListFinancial(Map<String, Object> params, FinancialProduct financialProduct) {
        EntityWrapper<FinancialProduct> wrapper = new EntityWrapper<FinancialProduct>();
        Integer productId = financialProduct.getProductId();
        if(productId != null){
            EntityWrapper<Product> wrapperCategory = new EntityWrapper<Product>();
            wrapperCategory.eq("productId", productId);
            //List<Object> financialIds = financialProductDao.selectObjs(wrapperCategory);
//            if (financialIds.isEmpty()) {
//                return new PageUtils(params);
//            }

        }
        return null;
    }

    /**
     * 保存
     * 理财产品
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
}
