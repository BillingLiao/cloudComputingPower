package com.ant.admin.service.impl;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.dao.MinerProductDao;
import com.ant.admin.dto.MinerProduct;
import com.ant.admin.service.MinerProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:40
 */
@Service("minerProductService")
public class MinerProductServiceImpl extends ServiceImpl<MinerProductDao,MinerProduct> implements MinerProductService {

    @Override
    public MinerProduct infoMiner(Integer productId) throws Exception {
        return null;
    }

    @Override
    public PageUtils ListMiner(Map<String, Object> params, MinerProduct minerProduct) {
        return null;
    }

    @Override
    public void insertMiner(MinerProduct minerProduct) {

    }
}
