package com.ant.webPage.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.common.validator.ValidatorUtils;
import com.ant.admin.controller.AbstractController;
import com.ant.admin.service.CloudProductService;
import com.ant.admin.service.ProductService;
import com.ant.entity.CloudProduct;
import com.ant.entity.Product;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * 云算力产品controller
 *
 * @author Billing
 * @date 2018/8/15 11:17
 */
@RestController
@RequestMapping("/user/cloud")
public class CloudProductController extends AbstractController{

    @Autowired
    private CloudProductService cloudProductService;

    /**
     * 列表
     * 云算力产品
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

    /**
     *
     * @param productId
     * @return
     */
    @RequestMapping("/info/{productId}")
    public Result info(@PathVariable("productId") Integer productId){
        CloudProduct cloudProduct =  cloudProductService.infoCloud(productId);
        return Result.ok().put("cloudProduct",cloudProduct);
    }

}
