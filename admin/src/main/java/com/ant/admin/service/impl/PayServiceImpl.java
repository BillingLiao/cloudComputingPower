package com.ant.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Query;
import com.ant.admin.dao.PayDao;
import com.ant.admin.service.PayService;
import com.ant.entity.phone.Pay;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:00
 */
@Service("payService")
public class PayServiceImpl extends ServiceImpl<PayDao,Pay> implements PayService {

    @Override
    public PageUtils queryPage(Map<String, Object> params, Pay pay) {
        EntityWrapper ew =new EntityWrapper<Pay>();
        Map<String, Object> map = BeanUtil.beanToMap(pay, true, true);
        map.forEach((k,v)->{
            ew.like(k, v.toString());
        });
        Page<Pay> page = this.selectPage(
                new Query<Pay>(params).getPage(),
                ew
        );

        return new PageUtils(page);
    }
}
