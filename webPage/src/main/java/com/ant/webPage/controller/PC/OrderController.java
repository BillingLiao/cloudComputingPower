package com.ant.webPage.controller.PC;

import com.ant.entity.phone.User;
import com.ant.webPage.service.PC.PCOrderServer;
import com.ant.webPage.util.MMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowCredentials="true")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private PCOrderServer pcOrderServer;

    @RequestMapping("/order_info")
    public PrInfo OrderInfo(@SessionAttribute User user){
        return MMP.Success(pcOrderServer.OrderAll(user.getUserId()));
    }

    @PostMapping("/order_user_update")
    public PrInfo OrderUserUpdate(@RequestParam("id") String id,@RequestParam("num") String num) throws Exception {
        return MMP.Success("暂未实现");
    }

    @RequestMapping("/order_one_info")
    public PrInfo OrderOneInfo(@SessionAttribute User user,@RequestParam("order_id") String id){
        return MMP.Success(pcOrderServer.OrderOne(user.getUserId(),id));
    }


}
