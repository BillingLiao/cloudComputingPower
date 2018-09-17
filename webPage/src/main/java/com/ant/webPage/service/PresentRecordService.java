package com.ant.webPage.service;

import com.ant.entity.PresentRecord;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author Billing
 * @date 2018/9/17 11:39
 */
public interface PresentRecordService  extends IService<PresentRecord> {

    PresentRecord selectPresentRecord(Integer userId);
}
