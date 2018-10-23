package com.ant.admin.service;

import com.ant.admin.common.utils.PageUtils;
import com.ant.entity.phone.Pay;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * 支付单表
 *
 * @author Billing
 * @date 2018/8/13 19:02
 */
public interface PayService extends IService<Pay>{

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params, Pay pay);
}
