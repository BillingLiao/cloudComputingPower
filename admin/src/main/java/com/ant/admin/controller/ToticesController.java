package com.ant.admin.controller;

import com.ant.admin.common.utils.Result;
import com.ant.admin.entity.Totices;
import com.ant.admin.service.ToticesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Billing
 * @date 2018/8/10 16:50
 */
@RestController
@RequestMapping("/totices")
public class ToticesController {

    @Autowired
    private ToticesService toticesService;

    /**
     * 保存
     * @param totices
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestBody Totices totices){
        int resultTotal = 0;
        if(totices.getToticesId()==null){
            resultTotal = toticesService.addTotices(totices);
        }else{
            resultTotal = toticesService.updateTotices(totices);
        }

        if(resultTotal>0){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

}
