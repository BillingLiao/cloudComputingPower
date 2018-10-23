package com.ant.admin.service.impl;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.CouponDao;
import com.ant.admin.service.CouponService;
import com.ant.entity.phone.Coupon;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 优惠券impl类
 * @author Billing
 * @date 2018/8/13 19:00
 */
@Service("couponService")
public class CouponServiceImpl extends ServiceImpl<CouponDao, Coupon> implements CouponService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<Coupon> couponEntityWrapper = new EntityWrapper<Coupon>();
        Page<Coupon> page =new Query<Coupon>(params).getPage();
        page.setRecords(baseMapper.selectList(page,couponEntityWrapper));
        PageUtils pageUtil = new PageUtils(page);
        return new PageUtils(page);
    }
}
