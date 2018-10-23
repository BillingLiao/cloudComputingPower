package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.service.PayService;
import com.ant.common.validator.ValidatorUtils;
import com.ant.entity.phone.Pay;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Billing
 * @date 2018/10/23 14:13
 */
@RestController
@RequestMapping("pay")
public class PayController {

    @Autowired
    private PayService payService;

    /**
     * 列表
     */
    @RequestMapping("/page")
    @RequiresPermissions("pay:list")
    public Result page(@RequestParam Map<String, Object> params, Pay pay){

        PageUtils page = payService.queryPage(params, pay);

        return Result.ok().put("page", page);

    }

    /**
     * 信息
     */
    @RequestMapping("/info/{payId}")
    @RequiresPermissions("pay:info")
    public Result info(@PathVariable("payId") Integer payId){
        Pay pay = payService.selectById(payId);

        return Result.ok().put("pay", pay);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("pay:save")
    public Result save(@RequestBody Pay pay){
        ValidatorUtils.validateEntity(pay);
        payService.insert(pay);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("pay:update")
    public Result update(@RequestBody Pay pay){
        ValidatorUtils.validateEntity(pay);
        payService.updateAllColumnById(pay);//全部更新

        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("pay:delete")
    public Result delete(@RequestBody Integer[] payIds){
        payService.deleteBatchIds(Arrays.asList(payIds));

        return Result.ok();
    }
}
