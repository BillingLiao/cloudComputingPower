package com.ant.admin.service.impl;

import com.ant.admin.dao.CategoryDao;
import com.ant.entity.phone.Category;
import com.ant.admin.service.CategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Billing
 * @date 2018/8/13 19:17
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao,Category> implements CategoryService {
}
