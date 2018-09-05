package com.ant.webPage.dao;

import com.ant.entity.User;

/**
 * @author Billing
 * @date 2018/9/5 15:21
 */
public interface UserDao {

    public User findOneById(int userId);
}
