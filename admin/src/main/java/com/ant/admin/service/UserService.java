package com.ant.admin.service;

import com.ant.admin.entity.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @author Billing
 * @date 2018/8/13 19:35
 */
public interface UserService extends IService<User> {

    /**
     * 查询用户的所有菜单ID
     */
    List<Integer> queryAllMenuId(Integer userId);
}
