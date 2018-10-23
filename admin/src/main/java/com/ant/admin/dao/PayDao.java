package com.ant.admin.dao;

import com.ant.entity.phone.Pay;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 支付单表
 *
 * @author Billing
 * @date 2018/8/13 18:50
 */
public interface PayDao extends BaseMapper<Pay> {

    /*List<Pay> selectList(Page<Pay> page, @Param("ew") Wrapper<Pay> wrapper);*/

}
