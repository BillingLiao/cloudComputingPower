package com.ant.webPage.controller.PC;

import com.aliyuncs.exceptions.ClientException;
import com.ant.webPage.service.PC.PCSingUpServer;
import com.ant.webPage.util.MMP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowCredentials = "true")
public class PCSinUpController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PCSingUpServer singUpServer;

    @GetMapping("/sign_up")
    public PrInfo SinUp(){
        return MMP.Error(401,"不能使用此种方式注册",null);
    }

    @PostMapping("/sign_up")
    public PrInfo SingUp(@RequestParam("phone") String phone,@RequestParam("pass") String pass,@RequestParam("code") String code){
        log.info(phone+":"+pass+":"+code);
        return MMP.Success(singUpServer.SingUp(phone,pass,code,""));
    }

    @PostMapping("/sing_up/{code}")
    public PrInfo SingUp(@RequestParam String phone, @RequestParam String pass, @RequestParam String code ,@PathVariable(value = "code") String iCode){
        return MMP.Success(singUpServer.SingUp(phone,pass,code,iCode));
    }

    @RequestMapping("/captcha/{phone}")
    public Object SingUpPhoneCode(@PathVariable(value = "phone") String phone) throws ClientException {
        return MMP.Success(singUpServer.SingUpPhoneCode(phone));
    }
}
