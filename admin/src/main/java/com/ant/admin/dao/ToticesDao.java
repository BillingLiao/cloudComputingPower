package com.ant.admin.dao;

import com.ant.admin.entity.Totices;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 公告mapper
 *
 * @author Billing
 * @date 2018-8-13 16:25
 */
@Component
public interface ToticesDao {

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
