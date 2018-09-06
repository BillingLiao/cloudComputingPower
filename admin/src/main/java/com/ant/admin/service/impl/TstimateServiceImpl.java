package com.ant.admin.service.impl;

import com.ant.admin.dao.TstimateDao;
import com.ant.admin.service.TstimateService;
import com.ant.entity.Tstimate;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:40
 */
@Service("tstimateService")
public class TstimateServiceImpl extends ServiceImpl<TstimateDao,Tstimate> implements TstimateService {


    @Autowired
    private TstimateService tstimateService;

    @Override
    public Tstimate infoTstimate(Integer tstimateId) {
        return null;
    }

    @Override
    public void insertTstimate(Tstimate tstimate) {

    }

    @Override
    public void updateTstimate(Tstimate tstimate) {

    }

    @Override
    public void deleteTstimate(Integer[] productIds) {

    }

    @Override
    public Page<Tstimate> queryPage(Map<String, Object> params, Wrapper<Tstimate> wrapper) {
        return null;
    }
}
