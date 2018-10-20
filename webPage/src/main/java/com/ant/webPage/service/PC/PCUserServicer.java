package com.ant.webPage.service.PC;

import cn.hutool.core.lang.Assert;
import com.ant.common.validator.ValidatorUtils;
import com.ant.entity.PC.PCUser;
import com.ant.entity.phone.BtcAddr;
import com.ant.webPage.dao.BtcAddrDao;
import com.ant.webPage.service.UserService;
import com.ant.webPage.tool.CheckTool;
import com.ant.webPage.util.Constant;
import com.ant.webPage.util.Md5Util;
import com.ant.webPage.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PCUserServicer {

    @Autowired
    private UserService userService;

    @Autowired
    private BtcAddrDao btcAddrDao;

    @Autowired
    private RedisUtils redisUtils;

    public PCUser UserInfo(Integer id){
        com.ant.entity.phone.User userPhone = userService.findOne(id);
        PCUser userPc = new PCUser();
        userPc.setId(userPhone.getUserId());
        userPc.setPhone(userPhone.getTelphone());
        userPc.setPassword(userPhone.getPassword());
        userPc.setEmail(userPhone.getEmail());
        userPc.setName("");//暂时留空
        userPc.setSalt("");//不展示
        userPc.setImgUrl("");//留空
        userPc.setIDCardNo("");//留
        userPc.setIDCardImg(null);//留
        userPc.setWallet(new HashMap(){
            {
                put("BTC",userPhone.getBtc());
            }
        });
        return userPc;
    }

    public Map Watllet(String watllet,String type,Integer userId){
        if(!CheckTool.isString(watllet)){
            return null;
        }
        BtcAddr btcAddrEntity = new BtcAddr();
        btcAddrEntity.setUserId(userId);
        btcAddrEntity.setAddr(watllet);
        btcAddrEntity.setCreateAt(new Date());
        btcAddrDao.insert(btcAddrEntity);
        return new HashMap(){{
            put(type,watllet);
        }};
    }

    public Map Password(String phone,String pass,String pa,String code){
        ValidatorUtils.validateEntity(phone);
        Assert.notBlank(code,"短信验证码不能为空");
        String codeRedis = redisUtils.get(Constant.RESET_PASS_SMS_OVERTIME_KEY+phone);
        if(codeRedis == null)
            return new HashMap(){{put(phone,"验证码已经失效，请重新获取");}};
        if(!code.equals(codeRedis))
            return new HashMap(){{put(phone,"输入验证码有误，请重新填写");}};
        if(pass==null || "".equals(pass))
            return new HashMap(){{put(phone,"密码不能为空");}};
        if(pa==null || "".equals(pa))
            return new HashMap(){{put(phone,"请确认新密码");}};
        if(!pa.equals(pass))
            return new HashMap(){{put(phone,"二次密码输入不一致,请重新输入新密码");}};
        com.ant.entity.phone.User user = userService.findByPhone(phone);
        String salt = user.getSalt();
        Md5Util encoderMd5 = new Md5Util(salt, "MD5");
        String psw = encoderMd5.encode(pass);
        user.setPassword(psw);
        userService.updateAllColumnById(user);
        return new HashMap(){{put(phone,pass); }};
    }
}
