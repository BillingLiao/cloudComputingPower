package com.ant.webPage.service.impl;

import com.ant.entity.Proxy;
import com.ant.webPage.dao.ProxyDao;
import com.ant.webPage.service.ProxyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Billing
 * @date 2018/9/14 9:52
 */
@Service("proxyService")
public class ProxyServiceImpl  extends ServiceImpl<ProxyDao, Proxy> implements ProxyService {
}
