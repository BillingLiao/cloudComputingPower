package com.ant.webPage.controller;

import com.ant.entity.FinancialProduct;
import com.ant.webPage.service.FinancialProductService;
import com.ant.webPage.service.ProductService;
import com.ant.webPage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 理财产品controller
 *
 * @author Billing
 * @date 2018/8/15 11:17
 */
@RestController
@RequestMapping("/financial")
public class FinancialProductController{

    @Autowired
    private ProductService productService;

    @Autowired
    private FinancialProductService financialProductService;

    /**
     * 列表
     * 理财产品
     * @return
     */
    @RequestMapping("/findList")
    public Result List(){
        List<FinancialProduct> financialProductList = financialProductService.findList();
        return Result.ok().put("financialProductList", financialProductList);
    }

    /**
     *
     * @param productId
     * @return
     */
    @RequestMapping("/findOne/{productId}")
    public Result info(@PathVariable("productId") Integer productId){
        FinancialProduct financialProduct =  financialProductService.findOne(productId);
        return Result.ok().put("financialProduct",financialProduct);
    }

}
