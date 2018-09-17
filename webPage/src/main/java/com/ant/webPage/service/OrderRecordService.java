package com.ant.webPage.service;

import com.ant.entity.OrderRecord;
import com.baomidou.mybatisplus.service.IService;

/**
 * 订单service
 * @author Billing
 * @date 2018/9/5 14:55
 */
public interface OrderRecordService extends IService<OrderRecord> {

    OrderRecord selectOrderRecord(Integer userId);
}
