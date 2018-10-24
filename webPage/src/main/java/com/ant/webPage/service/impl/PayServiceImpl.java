package com.ant.webPage.service.impl;

import com.ant.common.tool.CodeConstant;
import com.ant.entity.phone.Order;
import com.ant.entity.phone.OrderRecord;
import com.ant.entity.phone.Pay;
import com.ant.entity.phone.User;
import com.ant.webPage.dao.OrderDao;
import com.ant.webPage.dao.OrderRecordDao;
import com.ant.webPage.dao.PayDao;
import com.ant.webPage.service.PayService;
import com.ant.webPage.tool.CheckTool;
import com.ant.webPage.tool.LoadFileTool;
import com.ant.webPage.util.Result;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;

/**
 * 支付单表
 * @author Billing
 * @date 2018/8/13 19:37
 */
@Service("payService")
public class PayServiceImpl extends ServiceImpl<PayDao, Pay> implements PayService {

    private static final Logger log = LoggerFactory.getLogger(PayServiceImpl.class);

    private String[] types = {".jpg", ".bmp", ".jpeg", ".png"};

    @Autowired
    private PayDao payDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderRecordDao orderRecordDao;

    /**
     * 更新支付单(上传支付凭证)
     * @param user
     * @param payId
     * @param file
     * @param cardNumber
     * @param openingBank
     * @param trueName
     * @return
     */
    @Transactional
    @Override
    public Result updatePay(User user, Integer payId, MultipartFile file, String cardNumber, String openingBank, String trueName){
        //查询支付订单是否存在
        log.info("上传方法，接受到：" + payId);
        if(!CheckTool.isString(cardNumber)){
            return Result.error("银行卡号不能为空");
        }
        Pay pay = payDao.selectById(payId);
        if(pay == null){
            return Result.error("修改的信息不存在");
        }
        if (file == null) {
            return Result.error("文件未选择");
        }
        String fileName = "";
        if (!file.isEmpty()) {
            String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            fileName = payId + "" + type;
            if (Arrays.asList(types).contains(type)) {
                try {
                    PayFile(file,payId,fileName);
                } catch (Exception e) {
                    //e.printStackTrace();
                    return Result.error(CodeConstant.SET_ERR,"图片上传失败");
                }
            } else {
                return Result.error("此格式不支持");
            }
        }else{
            return Result.error("文件不能为空");
        }
        //修改支付单信息
        Pay payUpdate = new Pay();
        log.info("将存储地址储存到数据库");
        payUpdate.setVoucherUrl("/img/pay_img/" + fileName);
        payUpdate.setUserId(user.getUserId());
        payUpdate.setOrderId(pay.getOrderId());
        payUpdate.setPayId(payId);
        payUpdate.setCardNumber(cardNumber);
        payUpdate.setOpeningBank(openingBank);
        payUpdate.setTrueName(trueName);
        payUpdate.setPayTime(new Date());
        payDao.updateAllColumnById(payUpdate);
        //修改订单状态
        Order order = orderDao.selectById(pay.getOrderId());
        order.setOrderStatus(1);
        orderDao.updateAllColumnById(order);
        OrderRecord orderRecord = new OrderRecord(order.getOrderId(),order.getOrderStatus(),new Date());
        orderRecordDao.insert(orderRecord);
        return Result.ok("修改成功");
    }

    /**
     * 上传图片(支付凭证)
     * @param file
     * @param payId
     * @param fileName
     * @throws Exception
     */
    private static void PayFile(MultipartFile file, Integer payId,String fileName) throws Exception {
        BufferedOutputStream out = null;
        FileOutputStream outs = null;
        log.info(LoadFileTool.Path());
        File fileSourcePath = new File(LoadFileTool.Path() + "/img/pay");
        if (!fileSourcePath.exists()) {
            fileSourcePath.mkdirs();
        }
        log.info("上传的文件名为：" + fileName);
        outs = new FileOutputStream(new File(fileSourcePath, fileName));
        out = new BufferedOutputStream(outs);
        out.write(file.getBytes());
        out.flush();
        outs.flush();
        out.close();
        outs.close();
    }
}
