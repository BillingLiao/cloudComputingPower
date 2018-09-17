package com.ant.webPage.dao;

import com.ant.entity.OrderRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 订单dao
 * @author Billing
 * @date 2018/8/13 19:22
 */
public interface OrderRecordDao extends BaseMapper<OrderRecord> {

    OrderRecord selectOrderRecord(Integer userId);
}
