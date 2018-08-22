package com.ant.admin.service.impl;

import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.CloudProductDao;
import com.ant.admin.dao.ProductDao;
import com.ant.admin.dto.CloudProduct;
import com.ant.admin.entity.Product;
import com.ant.admin.service.CloudProductService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:40
 */
@Service("cloudProductService")
public class CloudProductServiceImpl extends ServiceImpl<CloudProductDao,CloudProduct> implements CloudProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CloudProductDao cloudProductDao;

    @Override
    public CloudProduct infoCloud(Integer productId){
        CloudProduct cloudProduct = cloudProductDao.selectById(productId);
        return cloudProduct;
    }

    @Override
    public void insertCloud(CloudProduct cloudProduct) {
        //插入产品表数据
        Product product = new Product(cloudProduct);
        productDao.insert(product);
        //插入云算力产品明细表数据
        cloudProductDao.insert(cloudProduct);
    }

    @Override
    public Page<CloudProduct> queryPage(Map<String, Object> params, Wrapper<Product> wrapper) {
        Page<CloudProduct> page =new Query<CloudProduct>(params).getPage();
        return page.setRecords(baseMapper.selectProductList(page,wrapper));
    }
}
