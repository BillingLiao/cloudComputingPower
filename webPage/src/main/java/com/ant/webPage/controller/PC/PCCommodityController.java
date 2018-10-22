package com.ant.webPage.controller.PC;

import com.ant.entity.phone.User;
import com.ant.webPage.service.PC.PCCommodityServer;
import com.ant.webPage.util.MMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowCredentials="true")
@RestController
@RequestMapping("/commodity")
public class PCCommodityController {

    @Autowired
    private PCCommodityServer commodityServer;

    @RequestMapping("/commodity_info_all")
    public PrInfo CommodityInfoAll(){
        return MMP.Success(commodityServer.CommondityList());
    }

    @RequestMapping("/commodity_info_id")
    public PrInfo CommodityInfoId(@RequestParam("id") String id){
        return MMP.Success(commodityServer.CommondityOneList(id));
    }

    @PostMapping("/commodity_purchase")
    public PrInfo CommodityPurchase(@SessionAttribute User user,@RequestParam("id") String id, @RequestParam("num") String num) {
        return MMP.Success(commodityServer.CommodityPurchase(user,id,num));
    }
}
