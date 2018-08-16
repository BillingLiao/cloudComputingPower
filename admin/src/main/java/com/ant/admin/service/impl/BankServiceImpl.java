package com.ant.admin.service.impl;

import com.ant.admin.dao.CategoryDao;
import com.ant.admin.entity.Category;
import com.ant.admin.service.BankService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Billing
 * @date 2018/8/13 19:00
 */
@Service("bankService")
public class BankServiceImpl extends ServiceImpl<CategoryDao,Category> implements BankService{

}
