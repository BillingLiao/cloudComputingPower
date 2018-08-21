package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.common.validator.ValidatorUtils;
import com.ant.admin.dto.MinerProduct;
import com.ant.admin.service.MinerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 产品controller
 *
 * @author Billing
 * @date 2018/8/15 11:17
 */
@RestController
@RequestMapping("/miner")
public class MinerProductController {

    @Autowired
    private MinerProductService MinerproductService;

    /**
     * 列表
     * 理财产品
     * @param params
     * @param minerProduct
     * @return
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String,Object> params, MinerProduct minerProduct){
        PageUtils page = MinerproductService.ListMiner(params,minerProduct);
        return Result.ok().put("page", page);
    }

    @RequestMapping("/info/{productId}")
    public Result info(@PathVariable("productId") Integer productId) throws Exception {
        MinerProduct minerProduct =  MinerproductService.infoMiner(productId);
        return Result.ok().put("minerProduct",minerProduct);
    }
    /**
     * 保存
     * 理财产品
     *
     * @param minerProduct
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestBody(required = false) MinerProduct minerProduct){
        ValidatorUtils.validateEntity(minerProduct);
        MinerproductService.insertMiner(minerProduct);
        return Result.ok();
    }


}
