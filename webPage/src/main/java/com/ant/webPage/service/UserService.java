package com.ant.webPage.service;

import com.ant.entity.User;
import com.ant.webPage.model.Msg;
import com.baomidou.mybatisplus.service.IService;

/**
 * @author Billing
 * @date 2018/9/5 14:55
 */
public interface UserService extends IService<User> {

    /**
     * 通过id查找用户
     * @param userId
     * @return
     */
    public User findOne(int userId);

    public Msg login(String telphone,String password);
}