package com.ant.admin.controller;

import com.ant.admin.common.utils.Result;
import com.ant.admin.common.validator.ValidatorUtils;
import com.ant.admin.entity.Bank;
import com.ant.admin.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 银行卡服务表
 *
 * @author Billing
 * @date 2018/8/14 18:21
 */
@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    /**
     * 保存
     * @param bank
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody Bank bank){
        ValidatorUtils.validateEntity(bank);
        //bankService.insert(bank);

        return Result.ok();
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping(value = "/update")
    public Result update(@RequestBody Bank bank){
        ValidatorUtils.validateEntity(bank);
        //bankService.updateAllColumnById(bank);

        return Result.ok();
    }

    @RequestMapping(value = "/delete")
    public Result delete(@RequestBody Integer[] bankIds){
        return Result.ok();
    }

}
