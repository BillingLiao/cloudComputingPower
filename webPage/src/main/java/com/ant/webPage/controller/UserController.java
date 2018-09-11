package com.ant.webPage.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.exceptions.ClientException;
import com.ant.common.validator.ValidatorUtils;
import com.ant.entity.User;
import com.ant.webPage.service.UserService;
import com.ant.webPage.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Billing
 * @date 2018/9/5 15:09
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController{

    @Autowired private UserService userService;

    @Autowired private RedisUtils redisUtils;

    /**
     * 手机登录
     * @param telphone
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam("telphone") String telphone,@RequestParam("password") String password){
        Result result = userService.login(telphone,password);
        return result;
    }

    /**
     * 发送验证码
     */
    @GetMapping("sendCode")
    public Result sendCode(@RequestParam String phone,@RequestParam Integer msgType) throws ClientException {
        ValidatorUtils.validateEntity(phone);
        //注册发送验证码时，要判断用户是否有账号
        if(msgType == 1){
            User user = userService.findByPhone(phone);
            if(user != null){
                return Result.error("您已经注册过账号，请登录");
            }
        }
        String tempCode = "SMS_144375029";
        if( redisUtils.get(Constant.RESET_PASS_SMS_OVERTIME_KEY+phone)!= null) {
            return Result.error("发送验证码比较频繁，等一分钟之后再试试");
        }
        String code = RandomUtil.randomNumbers(6);
        Alimsg.sendSms(phone,tempCode,code);
        redisUtils.set(Constant.RESET_PASS_SMS_CODE_KEY+phone, code, 60 * 15);
        redisUtils.set(Constant.RESET_PASS_SMS_OVERTIME_KEY+phone, code, 60 * 1);
        return Result.ok("验证码获取成功").put("code",code);
    }

    /**
     * 注册
     * @param phone
     * @param password
     * @param code
     * @return
     */
    @PostMapping("register")
    public Result register(@RequestParam String phone,@RequestParam String password,@RequestParam String code){
        Assert.notBlank(code,"短信验证码不能为空");
        String codeRedis = redisUtils.get(Constant.RESET_PASS_SMS_CODE_KEY+phone);
        if(codeRedis == null) {
            return Result.error("验证码已经失效，请重新获取");
        }
        if(code.equals(codeRedis)) {
            return Result.error("输入验证码有误，请重新填写");
        }
        if(password==null || "".equals(password)){
            return Result.error("密码不能为空");
        }
        String salt = RandomUtil.randomString(20);
        Md5Util encoderMd5 = new Md5Util(salt, "MD5");
        String psw = encoderMd5.encode(password);
        BigDecimal btc = new BigDecimal(0);
        BigDecimal cny = new BigDecimal(0);

        //设置邀请码 5位随机数+时间戳
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMddhhmmss");
        String invitationCode = RandomUtil.randomString(5) + s.format(new Date());
        Date createTime = new Date();
        User user = new User(phone,psw,salt,btc,cny,invitationCode,1,createTime);
        return Result.ok();
    }

    /**
     * 修改密码
     */
    @PostMapping("resetPass")
    public Result resetPass(@RequestParam String phone,@RequestParam String password,@RequestParam String confirmPassword,@RequestParam String code){
        ValidatorUtils.validateEntity(phone);
        Assert.notBlank(code,"短信验证码不能为空");
        String codeRedis = redisUtils.get(Constant.RESET_PASS_SMS_CODE_KEY+phone);
        if(codeRedis == null) {
            return Result.error("验证码已经失效，请重新获取");
        }
        if(code.equals(codeRedis)) {
            return Result.error("输入验证码有误，请重新填写");
        }

        if(password==null || "".equals(password)){
            return Result.error("新密码不能为空");
        }
        if(confirmPassword==null || "".equals(confirmPassword)){
            return Result.error("请确认新密码");
        }
        if(!password.equals(confirmPassword)){
            return Result.error("二次密码输入不一致,请重新输入新密码");
        }
        User user = userService.findByPhone(phone);
        String salt = user.getSalt();
        Md5Util encoderMd5 = new Md5Util(salt, "MD5");
        String psw = encoderMd5.encode(password);
        user.setPassword(psw);
        userService.updateAllColumnById(user);
        return Result.ok("修改密码成功");
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        //ShiroUtils.logout();
        return "redirect:login.html";
    }
}
