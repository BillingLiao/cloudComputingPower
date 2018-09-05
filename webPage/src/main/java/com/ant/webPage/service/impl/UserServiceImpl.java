package com.ant.webPage.service.impl;

import com.ant.entity.User;
import com.ant.webPage.dao.UserDao;
import com.ant.webPage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Billing
 * @date 2018/9/5 15:15
 */
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /**
     * 通过id查找用户
     * @param userId
     * @return
     */
    @Override
    public User findOne(int userId) {
        return userDao.findOneById(userId);
    }
}
