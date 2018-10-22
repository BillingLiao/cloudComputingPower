package com.ant.webPage.service.PC;

import com.ant.entity.PC.PCCommondity;
import com.ant.entity.phone.CloudProduct;
import com.ant.entity.phone.FinancialProduct;
import com.ant.entity.phone.Product;
import com.ant.entity.phone.User;
import com.ant.webPage.dao.CloudProductDao;
import com.ant.webPage.dao.FinancialProductDao;
import com.ant.webPage.service.OrderService;
import com.ant.webPage.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PCCommodityServer {


    @Autowired
    private CloudProductDao cloudProductDao;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    /**
     * 查询全部产品
     * @return
     */
    public Object CommondityList(){
        //创建phone实体类
        List<CloudProduct> cloudProducts = cloudProductDao.findList();
        //创建PC实体List
        List<PCCommondity> commodity = new ArrayList<>();
        //赋值
        for (CloudProduct cloudProduct : cloudProducts){
            PCCommondity pcCommondity = new PCCommondity();
            pcCommondity.setCommodityId(""+cloudProduct.getCloudId());
            pcCommondity.setCommodityName(cloudProduct.getProductName());
            pcCommondity.setCommodityStock(""+cloudProduct.getSort());
            pcCommondity.setCommodityInitialStock(""+cloudProduct.getSort()+cloudProduct.getSellOut());
            pcCommondity.setCommodityMoney(""+cloudProduct.getRetailPrice());
            pcCommondity.setCommodityType(cloudProduct.getModel());
            pcCommondity.setCommodityTime(cloudProduct.getSaleTime());
            pcCommondity.setCommodityTerm(cloudProduct.getContractCycle());
            pcCommondity.setCommodityUrl(cloudProduct.getPicImg());
            pcCommondity.setCommodityintroduction(cloudProduct.getIntroduction());
            //计算百分比公式（暂时不写）////////////////////
            pcCommondity.setHHHHH(0);
            /////////////////////////////////////////
            commodity.add(pcCommondity);
        }
        return commodity;
    }

    /**
     * 单个id查询商品
     * @param id
     * @return
     */
    public Object CommondityOneList(String id){
        try {
            CloudProduct cloudProduct = cloudProductDao.selectByProductId(Integer.valueOf(id));
            PCCommondity pcCommondity = new PCCommondity();
            pcCommondity.setCommodityId(""+cloudProduct.getCloudId());
            pcCommondity.setCommodityName(cloudProduct.getProductName());
            pcCommondity.setCommodityStock(""+cloudProduct.getSort());
            pcCommondity.setCommodityInitialStock(""+cloudProduct.getSort()+cloudProduct.getSellOut());
            pcCommondity.setCommodityMoney(""+cloudProduct.getRetailPrice());
            pcCommondity.setCommodityType(cloudProduct.getModel());
            pcCommondity.setCommodityTime(cloudProduct.getSaleTime());
            pcCommondity.setCommodityTerm(cloudProduct.getContractCycle());
            pcCommondity.setCommodityUrl(cloudProduct.getPicImg());
            pcCommondity.setCommodityintroduction(cloudProduct.getIntroduction());
            //计算百分比公式（暂时不写）////////////////////
            pcCommondity.setHHHHH(0);
            ////////////////////////////////////////////
            return pcCommondity;
        }catch (Exception e){
            return "不能输入一些奇怪的东西！";
        }
    }

    /**
     * 购买商品
     * @param user
     * @param commondityId
     * @param num
     * @return
     */
    public Object CommodityPurchase(User user, String commondityId, String num){
        try {
            CloudProduct cloudProduct = cloudProductDao.selectByProductId(Integer.valueOf(commondityId));
            BigDecimal bigDecimal = cloudProduct.getRetailPrice().multiply(BigDecimal.valueOf(Long.valueOf(num)));
            Product product = productService.selectById(Integer.valueOf(commondityId));
            return orderService.addCloudOrder(user, product, BigDecimal.valueOf(Long.valueOf(num)),bigDecimal);
        }catch (Exception e){
            return "溜溜溜溜溜";
        }
    }
}
