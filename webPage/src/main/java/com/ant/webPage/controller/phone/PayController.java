package com.ant.webPage.controller.phone;

import com.ant.entity.phone.User;
import com.ant.webPage.service.PayService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Billing
 * @date 2018/10/23 14:59
 */
@RestController
@RequestMapping("/pay")
@CrossOrigin
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping("/update")
    public Result update(@SessionAttribute User user, @RequestParam Integer payId,@RequestParam MultipartFile file, @RequestParam String cardNumber, @RequestParam(required = false) String openingBank, @RequestParam(required = false) String trueName){
        return payService.updatePay(user,payId,file,cardNumber,openingBank,trueName);
    }

}
