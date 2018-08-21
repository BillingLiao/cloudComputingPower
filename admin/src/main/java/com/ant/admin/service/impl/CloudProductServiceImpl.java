package com.ant.admin.service.impl;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.dao.CloudProductDao;
import com.ant.admin.dto.CloudProduct;
import com.ant.admin.service.CloudProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:40
 */
@Service("cloudProductService")
public class CloudProductServiceImpl extends ServiceImpl<CloudProductDao,CloudProduct> implements CloudProductService {

    @Override
    public CloudProduct infoCloud(Integer productId){
        return null;
    }

    @Override
    public PageUtils ListCloud(Map<String, Object> params, CloudProduct cloudProduct) {
        return null;
    }

    @Override
    public void insertCloud(CloudProduct cloudProduct) {
    }
}
