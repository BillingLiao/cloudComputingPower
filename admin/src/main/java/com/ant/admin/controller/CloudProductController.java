package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.common.validator.ValidatorUtils;
import com.ant.admin.dto.CloudProduct;
import com.ant.admin.entity.Product;
import com.ant.admin.service.CloudProductService;
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
@RequestMapping("/cloud")
public class CloudProductController extends AbstractController{

    @Autowired
    private CloudProductService cloudProductService;

    /**
     * 列表
     * 理财产品
     * @param params
     * @param cloudProduct
     * @return
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String,Object> params, CloudProduct cloudProduct){
        EntityWrapper<Product> wrapper = new EntityWrapper<Product>();
        wrapper.like("product.product_name", cloudProduct.getProductName());
        PageUtils page = new PageUtils(cloudProductService.queryPage(params, wrapper));
        return Result.ok().put("page", page);
    }

    @RequestMapping("/info/{productId}")
    public Result info(@PathVariable("productId") Integer productId) throws Exception {
        CloudProduct cloudProduct =  cloudProductService.infoCloud(productId);
        return Result.ok().put("cloudProduct",cloudProduct);
    }
    /**
     * 保存
     * 理财产品
     * @param cloudProduct
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestBody(required = false) CloudProduct cloudProduct){
        ValidatorUtils.validateEntity(cloudProduct);
        cloudProductService.insertCloud(cloudProduct);
        return Result.ok();
    }


}
