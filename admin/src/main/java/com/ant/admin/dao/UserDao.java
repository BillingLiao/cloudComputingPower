package com.ant.admin.dao;

import com.ant.admin.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 用户表
 *
 * @author Billing
 * @date 2018/8/13 19:27
 */
public interface UserDao extends BaseMapper<User> {

    /**
     * 查询用户信息
     * @param userName
     * @return
     */
    User getUserByName(String userName);

    /**
     * 查询用户的所有菜单
     *
     * @param userId
     * @return
     */
    List<Integer> queryAllMenuId(Integer userId);

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Integer userId);
}
