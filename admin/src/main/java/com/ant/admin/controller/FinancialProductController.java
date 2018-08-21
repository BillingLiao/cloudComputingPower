package com.ant.admin.controller;

import com.ant.admin.common.utils.PageUtils;
import com.ant.admin.common.utils.Result;
import com.ant.admin.common.validator.ValidatorUtils;
import com.ant.admin.dto.FinancialProduct;
import com.ant.admin.entity.Product;
import com.ant.admin.service.FinancialProductService;
import com.ant.admin.service.ProductService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
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
@RequestMapping("/financial")
public class FinancialProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FinancialProductService financialProductService;

    /**
     * 列表
     * 理财产品
     * @param params
     * @param financialProduct
     * @return
     */
    @RequestMapping("/list")
    public Result List(@RequestParam Map<String,Object> params, FinancialProduct financialProduct){
        EntityWrapper<Product> wrapper = new EntityWrapper<Product>();
        wrapper.like("product.product_name", financialProduct.getProductName());
        PageUtils page = new PageUtils(financialProductService.queryPage(params, wrapper));
        return Result.ok().put("page", page);
    }

    /**
     *
     * @param productId
     * @return
     * @throws Exception
     */
    @RequestMapping("/info/{productId}")
    public Result info(@PathVariable("productId") Integer productId){
        FinancialProduct financialProduct =  financialProductService.infoFinancial(productId);
        return Result.ok().put("financialProduct",financialProduct);
    }
    /**
     * 保存
     * 理财产品
     *
     * @param financialProduct
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestBody(required = false) FinancialProduct financialProduct){
        ValidatorUtils.validateEntity(financialProduct);
        financialProductService.insertProduct(financialProduct);
        return Result.ok();
    }

    /**
     * 删除
     * @param productIds
     * @return
     */
    @RequestMapping("delete")
    public Result delete(@RequestBody Integer[] productIds){
        for(int i=0;i<productIds.length;i++){
            Integer productId=productIds[i];
            EntityWrapper<FinancialProduct> orderEntityWrapper=new EntityWrapper<FinancialProduct>();
            Wrapper<FinancialProduct> financialWrapper=orderEntityWrapper.eq("id",productId);
            FinancialProduct financialProduct=financialProductService.selectOne(financialWrapper);
            Integer proId = financialProduct.getProductId();
            Product product = productService.selectById(proId);
            financialProduct.setDelFlag(1);
            product.setDelFlag(1);
            financialProductService.updateAllColumnById(financialProduct);
            productService.updateAllColumnById(product);
        }
        return Result.ok("订单产品已删除");
    }

}
