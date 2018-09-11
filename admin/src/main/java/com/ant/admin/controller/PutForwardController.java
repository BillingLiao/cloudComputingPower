package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.common.validator.ValidatorUtils;
import com.ant.admin.service.OrderService;
import com.ant.admin.service.PutForwardService;
import com.ant.entity.Order;
import com.ant.entity.PutForward;
import com.ant.entity.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * 提现记录controller
 * @author Billing
 * @date 2018/8/15 11:54
 */
@RestController
@RequestMapping("/putForward")
public class PutForwardController extends  AbstractController{

    @Autowired
    private PutForwardService putForwardService;

    /**
     * 列表
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("putForward:list")
    public Result list(@RequestParam Map<String,Object> params){
        PageUtils page=putForwardService.queryPage(params);
        return Result.ok().put("page", page);
    }

    /**
     * 更新提现状态
     */
    @RequestMapping("/status")
    @RequiresPermissions("putForward:status:update")
    public Result update(@RequestBody PutForward putForward) throws ParseException {
        //校验类型
        ValidatorUtils.validateEntity(putForward);
        if(putForward.getForwardStatus() == 3){
            putForward.setCompletionTime(new Date());
        }
        putForwardService.updateAllColumnById(putForward);
        return Result.ok("更新成功");
    }

    /**
     * 根据id查询单条记录
     * @param putForwardId
     * @return
     */
    @RequestMapping("/info/{putForwardId}")
    @RequiresPermissions("putForward:info")
    public Result info(@PathVariable("putForwardId") Integer putForwardId){
        PutForward putForward =  putForwardService.selectById(putForwardId);
        return Result.ok().put("putForward",putForward);
    }
}
