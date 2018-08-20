package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.common.validator.ValidatorUtils;
import com.ant.admin.dto.FinancialProduct;
import com.ant.admin.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 列表
     * 理财产品
     * @param params
     * @param financialProduct
     * @return
     */
    @RequestMapping("/listFinancial")
    public Result ListFinancial(@RequestParam Map<String,Object> params, FinancialProduct financialProduct){
        PageUtils page = productService.ListFinancial(params,financialProduct);
        return Result.ok().put("page", page);
    }

    @RequestMapping("/infoFinancial")
    public Result infoFinancial(@PathVariable("productId") Integer productId) throws Exception {
        FinancialProduct financialProduct =  productService.infoFinancial(productId);
        return Result.ok().put("financialProduct",financialProduct);
    }
    /**
     * 保存
     * 理财产品
     *
     * @param financialProduct
     * @return
     */
    @RequestMapping("/saveFinancial")
    public Result saveFinancial(@RequestBody(required = false) FinancialProduct financialProduct){
        ValidatorUtils.validateEntity(financialProduct);
        productService.insertProduct(financialProduct);
        return Result.ok();
    }


}
