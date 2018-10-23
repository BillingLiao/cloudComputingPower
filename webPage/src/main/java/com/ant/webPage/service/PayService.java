package com.ant.webPage.service;

import com.ant.entity.phone.Pay;
import com.ant.entity.phone.User;
import com.ant.webPage.util.Result;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 支付单表
 *
 * @author Billing
 * @date 2018/8/13 19:02
 */
public interface PayService extends IService<Pay>{

    /**
     * 更新支付单(上传支付凭证)
     * @param user 用户信息
     * @param payId 支付单编号
     * @param file 上传图片
     * @param cardNumber 银行卡
     * @param openingBank 开户行
     * @param trueName
     * @return
     */
    Result updatePay(User user, Integer payId, MultipartFile file, String cardNumber, String openingBank, String trueName);
}
