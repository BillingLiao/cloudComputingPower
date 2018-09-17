package com.ant.webPage.dao;

import com.ant.entity.PresentRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * @author Billing
 * @date 2018/9/15 14:52
 */
public interface PresentRecordDao extends BaseMapper<PresentRecord> {

    PresentRecord selectPresentRecord(Integer userId);
}
