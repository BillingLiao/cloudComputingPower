package com.ant.admin.service.impl;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.MinerProductDao;
import com.ant.admin.dao.ProductDao;
import com.ant.admin.dto.MinerProduct;
import com.ant.admin.entity.Product;
import com.ant.admin.service.MinerProductService;
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
@Service("minerProductService")
public class MinerProductServiceImpl extends ServiceImpl<MinerProductDao,MinerProduct> implements MinerProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private MinerProductDao minerProductDao;

    @Override
    public MinerProduct infoMiner(Integer productId){
        MinerProduct minerProduct = minerProductDao.selectById(productId);
        return minerProduct;
    }

    @Override
    public void insertMiner(MinerProduct minerProduct) {
        Product product = new Product(minerProduct);
        productDao.insert(product);
        minerProductDao.insert(minerProduct);
    }

    @Override
    public Page<MinerProduct> queryPage(Map<String, Object> params, Wrapper<Product> wrapper) {
        Page<MinerProduct> page =new Query<MinerProduct>(params).getPage();
        return page.setRecords(baseMapper.selectProductList(page,wrapper));
    }
}
