package com.ant.webPage.service;

import com.ant.entity.User;

/**
 * @author Billing
 * @date 2018/9/5 14:55
 */
public interface UserService {

    /**
     * 通过id查找用户
     * @param userId
     * @return
     */
    public User findOne(int userId);

    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    boolean updatePassword(Integer userId, String password, String newPassword);
}
