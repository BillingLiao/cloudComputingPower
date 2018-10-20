package com.ant.webPage.util;

import com.ant.webPage.controller.PC.PrInfo;

public class MMP {

    public static PrInfo Success(Object o){
        PrInfo info = new PrInfo();
        info.setCode(0);
        info.setPmsg("成功");
        info.setData(o);
        return info;
    }

    public static PrInfo Error(Integer s ,String msg ,Object o){
        PrInfo info = new PrInfo();
        info.setCode(s);
        info.setPmsg(msg);
        info.setData(o);
        return info;
    }
}
