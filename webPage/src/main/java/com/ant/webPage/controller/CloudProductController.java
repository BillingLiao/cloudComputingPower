package com.ant.webPage.controller;

import com.ant.entity.CloudProduct;
import com.ant.webPage.service.CloudProductService;
import com.ant.webPage.service.ProductService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 云算力产品controller
 *
 * @author Billing
 * @date 2018/8/15 11:17
 */
@RestController
@RequestMapping("/cloud")
public class CloudProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CloudProductService cloudProductService;

    /**
     * 列表
     * 云算力产品
     * @return
     */
    @RequestMapping("/findList")
    public Result list(){
        List<CloudProduct> cloudProductList = cloudProductService.findList();
        return Result.ok().put("cloudProductList", cloudProductList);
    }

    /**
     *
     * @param productId
     * @return
     */
    public Result info(@PathVariable("productId") Integer productId){
        CloudProduct cloudProduct =  cloudProductService.findOne(productId);
        return Result.ok().put("cloudProduct",cloudProduct);
    }
}
