package com.ant.admin.service.impl;

import com.ant.admin.dao.ProductDao;
import com.ant.admin.entity.Product;
import com.ant.admin.service.ProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Billing
 * @date 2018/8/13 19:38
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao,Product> implements ProductService {


}
