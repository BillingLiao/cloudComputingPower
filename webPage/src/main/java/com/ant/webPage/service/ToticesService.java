package com.ant.webPage.service;

import com.ant.entity.phone.Totices;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface ToticesService extends IService<Totices> {

    public List<Totices> findList();

    Totices selectOneById(Integer toticesId);
}

