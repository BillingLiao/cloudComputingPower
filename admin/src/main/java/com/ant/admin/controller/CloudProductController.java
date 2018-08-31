package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.common.validator.ValidatorUtils;
import com.ant.admin.service.ProductService;
import com.ant.dto.CloudProduct;
import com.ant.entity.Product;
import com.ant.admin.service.CloudProductService;
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
@RequestMapping("/cloud")
public class CloudProductController extends AbstractController{

    @Autowired
    private ProductService productService;

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
    @RequiresPermissions("product:cloud:list")
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
    @RequiresPermissions("product:cloud:info")
    public Result info(@PathVariable("productId") Integer productId){
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
    @RequiresPermissions("product:cloud:save")
    public Result save(@RequestBody(required = false) CloudProduct cloudProduct){
        ValidatorUtils.validateEntity(cloudProduct);
        cloudProductService.insertProduct(cloudProduct);
        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:cloud:update")
    public Result update(@RequestBody CloudProduct cloudProduct){
        ValidatorUtils.validateEntity(cloudProduct);
        cloudProductService.updateProduct(cloudProduct);
        return Result.ok();
    }

    /**
     * 删除
     * @param productIds
     * @return
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:cloud:delete")
    public Result delete(@RequestBody Integer[] productIds){
        cloudProductService.deleteProduct(productIds);
        return Result.ok("产品已删除");
    }

    /**
     * 修改上架 下架
     *
     */
    @RequestMapping("/updateShelve")
    @RequiresPermissions("product:cloud:update")
    public Result shelve(CloudProduct cloudProduct) throws IOException {
        logger.info("请求参数：{}", cloudProduct.toString());
        Product product = new Product(cloudProduct);
        productService.updateById(product);
        return Result.ok();
    }
}
