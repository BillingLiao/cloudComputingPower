package com.ant.webPage.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import com.ant.entity.User;
import com.ant.webPage.model.Msg;
import com.ant.webPage.service.UserService;
import com.ant.webPage.tool.CodeConstant;
import com.ant.webPage.util.Constant;
import com.ant.webPage.util.RedisUtils;
import com.ant.webPage.util.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Billing
 * @date 2018/9/5 15:09
 */
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired private UserService userService;

    @Autowired private RedisUtils redisUtils;

    @Autowired Msg msg;

    /**
     * 手机登录
     * @param telphone
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Msg login(@RequestParam String telphone, @RequestParam String password){
        msg = userService.login(telphone,password);
        return msg;
    }

    /**
     * 发送验证码(修改密码)
     */
    @GetMapping("sendCodeResetPass")
    public Msg sendCode(Integer userId){
        User user=userService.findOne(userId);
        String telphone= user.getTelphone();
        //ValidatorUtils.validateEntity(phone);
        if( redisUtils.get(Constant.RESET_PASS_SMS_OVERTIME_KEY+telphone)!= null) {
            msg.set("获取验证码太频繁，请等一分钟再来哦", CodeConstant.ERROR, null);
        }
        String number = RandomUtil.randomNumbers(6);
        SMSUtil.sendSMS(telphone, "尊敬的用户：您的校验码："+number+"，工作人员不会索取，请勿泄漏。", "0");
        redisUtils.set(Constant.RESET_PASS_SMS_CODE_KEY+telphone, number, 60 * 15);
        redisUtils.set(Constant.RESET_PASS_SMS_OVERTIME_KEY+telphone, number, 60 * 1);
        msg.set("验证码获取成功", CodeConstant.SUCCESS, number);
        return msg;
    }

    /**
     * 修改密码
     */
    @PostMapping("resetPass")
    public Msg resetPass(Integer userId,String password,String confirmPassword,String code){
        User user=userService.findOne(userId);
        String telphone = user.getTelphone();
        Assert.notBlank(code,"短信验证码不能为空");
        String codeRedis = redisUtils.get(Constant.RESET_PASS_SMS_CODE_KEY+telphone);
        if(codeRedis == null) {
            msg.set("验证码已经失效，请重新获取", CodeConstant.ERROR, null);
        }
        if(code.equals(codeRedis)) {
            msg.set("输入验证码有误，请重新填写", CodeConstant.ERROR, null);
        }

        if(password==null || "".equals(password)){
            msg.set("新密码不能为空", CodeConstant.ERROR, null);
        }
        if(confirmPassword==null || "".equals(confirmPassword)){
            msg.set("请确认新密码", CodeConstant.ERROR, null);
        }
        if(!password.equals(confirmPassword)){
            msg.set("二次密码输入不一致,请重新输入新密码", CodeConstant.ERROR, null);
        }
        user.setPassword(password);
        userService.updateAllColumnById(user);
        msg.set("修改密码成功",CodeConstant.SUCCESS,null);
        return msg;
    }
}
