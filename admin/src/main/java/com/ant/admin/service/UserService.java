package com.ant.admin.service;

import com.ant.admin.common.utils.PageUtils;
import com.ant.entity.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/8/13 19:35
 */
public interface UserService extends IService<User> {

    /**
     * 查询用户的所有菜单ID
     */
    List<Integer> queryAllMenuId(Integer userId);


    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存用户
     */
    void save(User user);

    /**
     * 修改用户
     */
    void update(User user);

    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    boolean updatePassword(Integer userId, String password, String newPassword);
}
