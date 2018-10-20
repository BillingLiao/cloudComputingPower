//package com.ant.webPage.controller.PC;
//
//import com.ant.entity.phone.PCUser;
//import com.ant.webPage.service.PC.PCUserServicer;
//import com.ant.webPage.util.MMP;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//
//@RestController
//@CrossOrigin(allowCredentials = "true")
//@RequestMapping("/user_pc")
//public class PCUserController {
//
//    @Autowired
//    private PCUserServicer userServicerPc;
//
//    @RequestMapping("/user_info")
//    public PrInfo UserInfo(@SessionAttribute PCUser user){
//        return MMP.Success(userServicerPc.UserInfo(user.getUserId()));
//    }
//
//    @RequestMapping("/password_retrieve")
//    public PrInfo PassswordRetrieve(@RequestParam String phone,@RequestParam String pass,@RequestParam String pa,@RequestParam String code){
//        return MMP.Success(userServicerPc.Password(phone,pass,pa,code));
//    }
//
//    @RequestMapping("/password_update")
//    public PrInfo PasswordUpdate(@RequestParam String phone,String passc, @RequestParam String pass,@RequestParam String pa,@RequestParam String code){
//        if (passc == null || "".equals(passc))
//            return MMP.Success(new HashMap(){{put(phone,"旧密码不能为空");}});
//        return MMP.Success(userServicerPc.Password(phone,pass,pa,code));
//    }
//
//    @RequestMapping("/user_img")
//    public PrInfo UserImg(){
//        return null;
//    }
//
//    @PostMapping("/card_img")
//    public PrInfo CardImg(){
//        return null;
//    }
//
//    @RequestMapping("/wallet")
//    public PrInfo Wallet(@SessionAttribute PCUser user, String addr){
//        return MMP.Success(userServicerPc.Watllet(addr,"BTC",user.getUserId()));
//    }
//
//
//
//}
