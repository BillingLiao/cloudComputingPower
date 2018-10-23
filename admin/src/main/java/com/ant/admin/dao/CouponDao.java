package com.ant.admin.dao;

import com.ant.entity.phone.Coupon;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券表
 *
 * @author Billing
 * @date 2018/8/13 18:50
 */
public interface CouponDao extends BaseMapper<Coupon> {

    List<Coupon> selectList(Page<Coupon> page, @Param("ew") Wrapper<Coupon> wrapper);

}
