package com.ant.webPage.service.impl;

import com.ant.entity.BtcAddr;
import com.ant.entity.PresentRecord;
import com.ant.entity.PutForward;
import com.ant.entity.User;
import com.ant.webPage.dao.BtcAddrDao;
import com.ant.webPage.dao.PresentRecordDao;
import com.ant.webPage.dao.PutForwardDao;
import com.ant.webPage.dao.UserDao;
import com.ant.webPage.service.BtcAddrService;
import com.ant.webPage.service.PutForwardService;
import com.ant.webPage.tool.CheckTool;
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
@Service("btcAddrService")
public class BtcAddrServiceImpl extends ServiceImpl<BtcAddrDao, BtcAddr> implements BtcAddrService {

    @Autowired
    private BtcAddrDao btcAddrDao;

    @Override
    public Result addBtcAddr(User user, String btcAddr) {
        if(!CheckTool.isString(btcAddr)){
            return Result.error("参数为空");
        }
        BtcAddr btcAddrEntity = new BtcAddr();
        btcAddrEntity.setUserId(user.getUserId());
        btcAddrEntity.setBtc_addr(btcAddr);
        btcAddrEntity.setCreateAt(new Date());
        btcAddrDao.insert(btcAddrEntity);
        return Result.ok("操作成功");
    }

    @Override
    public Result updateBtcAddr(User user, String btcAddr, Integer btcAddrId) {
        if(!CheckTool.isString(btcAddr)){
            return Result.error("参数为空");
        }
        BtcAddr btcAddrEntity = btcAddrDao.selectById(btcAddrId);
        if(btcAddrEntity == null){
            return Result.error("修改的信息不存在");
        }
        BtcAddr btcAddrUpade = new BtcAddr();
        btcAddrUpade.setUserId(user.getUserId());
        btcAddrUpade.setBtc_addr(btcAddr);
        btcAddrUpade.setBtcAddrId(btcAddrId);
        btcAddrDao.updateAllColumnById(btcAddrUpade);
        return Result.ok("操作成功");
    }
}
