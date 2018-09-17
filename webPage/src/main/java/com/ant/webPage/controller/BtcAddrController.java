package com.ant.webPage.controller;

import com.ant.entity.User;
import com.ant.webPage.service.BtcAddrService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Billing
 * @date 2018/9/15 17:44
 */
@RestController
@RequestMapping("/btcAddr")
@CrossOrigin
public class BtcAddrController {

    @Autowired
    private BtcAddrService btcAddrService;

    @RequestMapping("/add")
    public Result add(@SessionAttribute User user, String btcAddr, @RequestParam(required = false) Integer btcAddrId){
        if(btcAddrId == null){
            return btcAddrService.addBtcAddr(user,btcAddr);
        }else{
            return btcAddrService.updateBtcAddr(user,btcAddr,btcAddrId);
        }

    }
}
