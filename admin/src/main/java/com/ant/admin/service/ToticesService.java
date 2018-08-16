package com.ant.admin.service;

import com.ant.admin.entity.Totices;

import java.util.List;
import java.util.Map;

public interface ToticesService{

    /**
     * 添加产品
     * @param totices
     */
    public int addTotices(Totices totices);

    /**
     * 更新产品
     * @param totices
     * @return
     */
    public int updateTotices(Totices totices);

    /**
     * 删除产品
     * @param id
     * @return
     */
    public int deleteTotices(Integer id);

    /**
     * 分页查询
     * @param map
     * @return
     */
    public List<Totices> find(Map<String, Object> map);

    /**
     * 查询所有公告集合
     * @return
     */
    public List<Totices> findAll();

}

