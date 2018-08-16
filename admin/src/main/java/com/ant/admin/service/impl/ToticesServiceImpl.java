package com.ant.admin.service.impl;

import com.ant.admin.dao.ToticesDao;
import com.ant.admin.entity.Totices;
import com.ant.admin.service.ToticesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ToticesServiceImpl implements ToticesService{

    @Resource
    private ToticesDao toticesDao;

    @Override
    public int addTotices(Totices totices) {
        return toticesDao.addTotices(totices);
    }

    @Override
    public int updateTotices(Totices totices) {
        return toticesDao.updateTotices(totices);
    }

    @Override
    public int deleteTotices(Integer id) {
        return toticesDao.deleteTotices(id);
    }

    @Override
    public List<Totices> find(Map<String, Object> map) {
        return toticesDao.find(map);
    }

    @Override
    public List<Totices> findAll() {
        return toticesDao.findAll();
    }
}
