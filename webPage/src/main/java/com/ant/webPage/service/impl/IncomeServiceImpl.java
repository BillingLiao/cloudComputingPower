package com.ant.webPage.service.impl;

import com.ant.entity.Income;
import com.ant.webPage.dao.IncomeDao;
import com.ant.webPage.service.IncomeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 收益表service实现类
 * @author Billing
 * @date 2018/8/13 19:36
 */
@Service("incomeService")
public class IncomeServiceImpl extends ServiceImpl<IncomeDao,Income> implements IncomeService {

    @Autowired
    private IncomeDao incomeDao;


    @Override
    public BigDecimal selectCloudIncomeUser(Integer userId) {
        return incomeDao.selectCloudIncomeUser(userId);


    }

    @Override
    public BigDecimal selectFinancialIncomeUser(Integer userId){
        return incomeDao.selectFinancialIncomeUser(userId);
    }
}
