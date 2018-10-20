package com.ant.webPage.service;

import com.ant.entity.phone.CloudProduct;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:32
 */
public interface CloudProductService extends IService<CloudProduct> {

    public List<CloudProduct> findList();

    public CloudProduct findOne(Integer productId);

    public CloudProduct findFirst();
}
