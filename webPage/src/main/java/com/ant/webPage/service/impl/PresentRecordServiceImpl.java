package com.ant.webPage.service.impl;

import com.ant.entity.PresentRecord;
import com.ant.entity.PutForward;
import com.ant.entity.User;
import com.ant.webPage.dao.PresentRecordDao;
import com.ant.webPage.dao.PutForwardDao;
import com.ant.webPage.dao.UserDao;
import com.ant.webPage.service.PresentRecordService;
import com.ant.webPage.service.PutForwardService;
import com.ant.webPage.util.Result;
import com.ant.webPage.util.SerialNumberUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("presentRecordService")
public class PresentRecordServiceImpl extends ServiceImpl<PresentRecordDao, PresentRecord> implements PresentRecordService {

    @Autowired
    private PresentRecordDao presentRecordDao;

    @Override
    public PresentRecord selectPresentRecord(Integer userId) {
        PresentRecord presentRecord = presentRecordDao.selectPresentRecord(userId);
        return presentRecord;
    }
}
