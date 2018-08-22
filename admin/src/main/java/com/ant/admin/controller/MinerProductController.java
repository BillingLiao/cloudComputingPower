package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.common.validator.ValidatorUtils;
import com.ant.admin.dto.MinerProduct;
import com.ant.admin.entity.Product;
import com.ant.admin.service.MinerProductService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
    private MinerProductService minerproductService;

    /**
     * 列表
     * 理财产品
     * @param params
     * @param minerProduct
     * @return
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String,Object> params, MinerProduct minerProduct){
        EntityWrapper<Product> wrapper = new EntityWrapper<Product>();
        wrapper.like("product.product_name", minerProduct.getProductName());
        PageUtils page = new PageUtils(minerproductService.queryPage(params, wrapper));
        return Result.ok().put("page", page);
    }

    @RequestMapping("/info/{productId}")
    public Result info(@PathVariable("productId") Integer productId) throws Exception {
        MinerProduct minerProduct =  minerproductService.infoMiner(productId);
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
        minerproductService.insertMiner(minerProduct);
        return Result.ok();
    }


}
