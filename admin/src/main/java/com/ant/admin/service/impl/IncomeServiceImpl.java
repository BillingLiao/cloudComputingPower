package com.ant.admin.service.impl;

import com.ant.admin.dao.IncomeDao;
import com.ant.entity.Income;
import com.ant.admin.service.IncomeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Billing
 * @date 2018/8/13 19:36
 */
@Service("incomeService")
public class IncomeServiceImpl extends ServiceImpl<IncomeDao,Income> implements IncomeService {

}
