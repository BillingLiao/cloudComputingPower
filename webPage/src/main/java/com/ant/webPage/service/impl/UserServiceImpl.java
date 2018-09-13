package com.ant.webPage.service.impl;

import com.ant.entity.User;
import com.ant.webPage.dao.UserDao;
import com.ant.webPage.service.UserService;
import com.ant.webPage.tool.TokenTool;
import com.ant.webPage.util.Md5Util;
import com.ant.webPage.util.Result;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Billing
 * @date 2018/9/5 15:15
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {

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
    public Result login(String telphone, String password){
        User user = userDao.findOneByPhone(telphone);
        if(user != null){
            String salt = user.getSalt();
            Md5Util encoderMd5 = new Md5Util(salt, "MD5");
            String psw = encoderMd5.encode(password);
            if(user.getPassword().equals(psw)) {
                String result = TokenTool.create(user.getUserId());
                return Result.ok("登录成功").put("result",result);
            } else {
                return Result.error("密码错误，请重新输入");
            }

        }else{
            return Result.error("该手机号还未注册");
        }
    }

    @Override
    public User findByPhone(String phone) {
        return userDao.findOneByPhone(phone);
    }

    @Override
    public User selectByInvitationCode(String invitationCode) {
        return userDao.selectByInvitationCode(invitationCode);
    }
}
