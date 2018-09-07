package com.ant.webPage.shcm.send;

import com.ant.webPage.shcm.bean.ReplyBean;
import com.ant.webPage.shcm.bean.SendStateBean;
import com.ant.webPage.shcm.util.HttpUtils;
import com.ant.webPage.shcm.util.PublicUtils;

import java.util.List;

/**
 * @author Billing
 * @date 2018/9/7 18:31
 */
public class DataApi {
    private static String sDataUrl = "http://smsapi.c123.cn/DataPlatform/DataApi";
    private static String sAccount = "";
    private static String sAuthKey = "";

    public DataApi() {
    }

    public static void initialzeAccount(String url, String account, String authkey) {
        sDataUrl = url;
        sAccount = account;
        sAuthKey = authkey;
    }

    public static String querySendState() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("action=getSendState&ac=");
        sb.append(sAccount);
        sb.append("&authkey=");
        sb.append(sAuthKey);
        return HttpUtils.post(sDataUrl, sb.toString(), "GET", "UTF-8");
    }

    public static String queryReply() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("action=getReply&ac=");
        sb.append(sAccount);
        sb.append("&authkey=");
        sb.append(sAuthKey);
        return HttpUtils.post(sDataUrl, sb.toString(), "GET", "UTF-8");
    }

    public static List<SendStateBean> getSendState() {
        try {
            String sRet = querySendState();
            return PublicUtils.parseSendState(sRet);
        } catch (Exception var1) {
            return null;
        }
    }

    public static List<ReplyBean> getReply() {
        try {
            String sRet = queryReply();
            return PublicUtils.parseReply(sRet);
        } catch (Exception var1) {
            return null;
        }
    }
}
