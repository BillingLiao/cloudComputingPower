package com.ant.admin.service;

import com.ant.admin.common.utils.PageUtils;
import com.ant.entity.phone.Bank;
import com.ant.entity.phone.Coupon;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * 优惠券表
 *
 * @author Billing
 * @date 2018/8/13 19:02
 */
public interface CouponService extends IService<Coupon>{

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);
}
