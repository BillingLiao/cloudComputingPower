package com.ant.webPage.service.PC;

import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.ant.entity.PC.PCUser;
import com.ant.entity.phone.Proxy;
import com.ant.entity.phone.User;
import com.ant.webPage.service.ProxyService;
import com.ant.webPage.service.UserService;
import com.ant.webPage.tool.CheckTool;
import com.ant.webPage.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Service
public class PCSingUpServer {

    public Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private ProxyService proxyService;


    /**
     * 注册方法
     * @param phone
     * @param pass
     * @param code
     * @return
     */
    public Object SingUp(String phone,String pass,String code,String invitationCode){
        log.info(phone+":"+pass+":"+code);
        if (code == null || "".equals(code))
            return "验证码不能为空";
        //获取短信验证码
        String codeRedis = redisUtils.get(Constant.RESET_PASS_SMS_OVERTIME_KEY+phone);
        log.info("redis获取到的短信验证码："+codeRedis);
        if (codeRedis == null)
            return "验证已过期";
        if (!code.equals(codeRedis))
            return "验证码不正确哦";
        if (pass == null || "".equals(pass) )
            return "没有密码怎么注册";

        //写入注册信息/./././././././././././././././././././././.
        String salt = RandomUtil.randomString(20);
        Md5Util encoderMd5 = new Md5Util(salt, "MD5");
        String psw = encoderMd5.encode(pass);
        BigDecimal btc = new BigDecimal(0);
        BigDecimal cny = new BigDecimal(0);
        Date createTime = new Date();
        User user = new User(phone,psw,salt,btc,cny,1,createTime);
        //././././././././././././././././././././././././././././././././.

        //判断是否为被邀请用户
        if (CheckTool.isString(invitationCode)){
            //判断能否找到邀请用户
            User fatherUser = userService.selectByInvitationCode(invitationCode);
            if (fatherUser == null) {
                return "邀请码有误，请重新填写";
            }else{
                //设置邀请码 5位随机数
                Proxy fatherProxy = new Proxy();
                fatherProxy.setUserId(fatherUser.getUserId());
                //设置子代理
                fatherProxy.setSonId(user.getUserId());

                userService.insert(user);
                //设置邀请码 5位随机数
                String invitationCodeNew = SerialNumberUtil.toSerialNumber(user.getUserId());
                user.setInvitationCode(invitationCodeNew);
                log.info("PC接口，新用户【"+phone+"】注册，邀请码【"+invitationCodeNew+"】，被邀请码【"+invitationCode+"】");
                //写入数据库
                userService.updateAllColumnById(user);
                proxyService.insert(fatherProxy);
            }
        }else {
            userService.insert(user);
            String invitationCodeNew = SerialNumberUtil.toSerialNumber(user.getUserId());
            user.setInvitationCode(invitationCodeNew);
            log.info("PC接口，新用户【"+phone+"】注册，邀请码【"+invitationCodeNew+"】");
            userService.updateAllColumnById(user);
        }
        return user;
    }


    /**
     * 注册短信发送
     * @param phone
     * @return
     * @throws ClientException
     */
    public Object SingUpPhoneCode(String phone) throws ClientException {
        //判断是否注册过了
        User user = userService.findByPhone(phone);
        if(user != null)
            return Result.error("您已经注册过账号");
        String tempCode = "SMS_144375029";
        //判断上一次验证码有没有过期
        if( redisUtils.get(Constant.RESET_PASS_SMS_CODE_KEY+phone)!= null)
            return "发送验证码比较频繁，等一分钟之后再试试";
        String verificationCode = RandomUtil.randomNumbers(4);
        SendSmsResponse response = Alimsg.sendSms(phone,tempCode,verificationCode);
        log.info("发送的短信验证码："+verificationCode);
        String resultCode = response.getCode();
        log.info("PC接口，注册短信发送记录【"+phone+"】");
        log.info(resultCode);
        if(!resultCode.equals("OK"))
            return "获取验证码失败，请重新获取";
        redisUtils.set(Constant.RESET_PASS_SMS_CODE_KEY+phone, verificationCode, 60 * 1);
        redisUtils.set(Constant.RESET_PASS_SMS_OVERTIME_KEY+phone, verificationCode, 60 * 5);
        return "验证码获取成功";
    }
}
