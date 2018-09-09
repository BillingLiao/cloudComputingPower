package com.ant.webPage.service.impl;

import com.ant.entity.User;
import com.ant.webPage.dao.UserDao;
import com.ant.webPage.model.Msg;
import com.ant.webPage.service.UserService;
import com.ant.webPage.tool.CodeConstant;
import com.ant.webPage.tool.TokenTool;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

/**
 * @author Billing
 * @date 2018/9/5 15:15
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {

    @Autowired Msg msg;

    @Autowired UserDao userDao;

    /**
     * 通过id查找用户
     * @param userId
     * @return
     */
    @Override
    public User findOne(int userId) {
        return userDao.findOneById(userId);
    }


    @Override
    public Msg login(String telphone,String password){
        User user = userDao.findOneByPhone(telphone);
        if(user != null){
            //String password = MD5();
            if(user.getPassword().equals(password)) {
                String result = TokenTool.create(user.getUserId());
                //msg.set("登录成功", CodeConstant.SUCCESS, result);
                msg.setCode(CodeConstant.SUCCESS);
                //msg.setCode
            } else {
                //msg.set("用户名或密码错误", CodeConstant.ERROR, null);
                msg.setCode(CodeConstant.ERROR);
            }

        }else{
            //msg.set("用户名或密码错误", CodeConstant.ERROR, null);
            msg.setCode(CodeConstant.ERROR);
        }
        return msg;
    }
}
