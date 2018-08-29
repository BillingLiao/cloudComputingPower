package com.ant.admin.dao;

import com.ant.admin.entity.Order;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:22
 */
public interface OrderDao extends BaseMapper<Order> {

    /**
     * 分页查询
     * @param page
     * @param wrapper
     * @return
     */
    List<Order> selectOrderList(Page<Order> page,@Param("ew") Wrapper<Order> wrapper);
}
