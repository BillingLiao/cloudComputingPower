package com.ant.webPage.shcm.util;

import com.ant.webPage.shcm.bean.BalanceResultBean;
import com.ant.webPage.shcm.bean.ReplyBean;
import com.ant.webPage.shcm.bean.SendResultBean;
import com.ant.webPage.shcm.bean.SendStateBean;
import com.ant.webPage.shcm.bean.UpdateResultBean;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

/**
 * @author Billing
 * @date 2018/9/7 18:27
 */
public class PublicUtils {

    public PublicUtils() {
    }

    public static String UrlEncode(String sName, String encodeing) {
        String sValue = "";
        if (sName == null) {
            return sValue;
        } else {
            try {
                if (encodeing == null) {
                    sValue = URLEncoder.encode(sName, "UTF-8");
                } else {
                    sValue = URLEncoder.encode(sName, encodeing);
                }
            } catch (UnsupportedEncodingException var4) {
                ;
            }

            return sValue;
        }
    }

    public static String joinArray(String[] mobileArray, String delim) {
        String sMobile = "";
        if (mobileArray != null && mobileArray.length != 0) {
            String[] var6 = mobileArray;
            int var5 = mobileArray.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String m = var6[var4];
                sMobile = sMobile + m;
                sMobile = sMobile + delim;
            }

            if (sMobile.endsWith(delim)) {
                sMobile = sMobile.substring(0, sMobile.length() - delim.length());
            }

            return sMobile;
        } else {
            return sMobile;
        }
    }

    public static int parseInt(String str, int def) {
        try {
            return Integer.parseInt(str);
        } catch (Exception var3) {
            return def;
        }
    }

    public static double parseDouble(String str, double def) {
        try {
            return Double.parseDouble(str);
        } catch (Exception var4) {
            return def;
        }
    }

    public static long parseLong(String str, long def) {
        try {
            return Long.parseLong(str);
        } catch (Exception var4) {
            return def;
        }
    }

    public static Date parseDate(String str, String format) {
        Date d = null;

        try {
            SimpleDateFormat fmt = new SimpleDateFormat(format);
            d = fmt.parse(str);
        } catch (Exception var4) {
            ;
        }

        return d;
    }

    private static String getErrorMsg(int result) {
        switch(result) {
            case -11:
                return "扣费失败";
            case -10:
                return "内部错误";
            case -9:
                return "帐户余额不足";
            case -8:
                return "不可使用该通道组";
            case -7:
                return "帐户未开启接口发送";
            case -6:
                return "帐户已锁定或已过期";
            case -5:
                return "无此帐户";
            case -4:
                return "参数不正确";
            case -3:
                return "密钥已锁定";
            case -2:
                return "密钥不正确";
            case -1:
                return "服务器拒绝";
            case 0:
                return "帐户格式不正确";
            case 1:
                return "操作成功";
            default:
                return "未知错误";
        }
    }

    public static List<SendResultBean> parseResult(String xml) {
        StringReader read = new StringReader(xml);
        InputSource source = new InputSource(read);
        List<SendResultBean> listItem = new ArrayList();
        SAXBuilder builder = new SAXBuilder();

        try {
            Document doc = builder.build(source);
            Element root = doc.getRootElement();
            if (root != null) {
                SendResultBean t = new SendResultBean();
                Attribute result = root.getAttribute("result");
                if (result != null) {
                    t.setResult(parseInt(result.getValue(), 0));
                    t.setErrMsg(getErrorMsg(t.getResult()));
                }

                if (t.getResult() > 0) {
                    List<Element> listResult = root.getChildren("Item");
                    Iterator var11 = listResult.iterator();

                    while(var11.hasNext()) {
                        Element e = (Element)var11.next();
                        t.setCorpId(parseLong(e.getAttributeValue("cid"), 0L));
                        t.setStaffId(parseInt(e.getAttributeValue("sid"), 0));
                        t.setMsgId(parseLong(e.getAttributeValue("msgid"), 0L));
                        t.setTotal(parseInt(e.getAttributeValue("total"), 0));
                        t.setUnitPrice(parseDouble(e.getAttributeValue("price"), 0.0D));
                        int nRemain = 0;
                        double dRemain = parseDouble(e.getAttributeValue("remain"), 0.0D);
                        if (t.getUnitPrice() > 0.0D) {
                            nRemain = (int)Math.round(dRemain / t.getUnitPrice());
                        }

                        t.setRemain(nRemain);
                        listItem.add(t);
                    }
                }
            }
        } catch (JDOMException var15) {
            ;
        } catch (IOException var16) {
            ;
        }

        return listItem;
    }

    public static BalanceResultBean parseBalance(String xml) {
        StringReader read = new StringReader(xml);
        InputSource source = new InputSource(read);
        BalanceResultBean t = new BalanceResultBean();
        SAXBuilder builder = new SAXBuilder();

        try {
            Document doc = builder.build(source);
            Element root = doc.getRootElement();
            if (root != null) {
                Attribute result = root.getAttribute("result");
                if (result != null) {
                    int nResult = parseInt(result.getValue(), 0);
                    t.setResult(nResult);
                    t.setErrMsg(getErrorMsg(nResult));
                }

                if (t.getResult() > 0) {
                    Element item = root.getChild("Item");
                    if (item != null) {
                        t.setCorpId(parseLong(item.getAttributeValue("cid"), 0L));
                        t.setStaffId(parseInt(item.getAttributeValue("sid"), 0));
                        //int nRemain = false;
                        double dRemain = parseDouble(item.getAttributeValue("remain"), 0.0D);
                        int nRemain = (int)Math.round(dRemain / 0.1D);
                        t.setRemain(nRemain);
                    }
                }
            }
        } catch (JDOMException var12) {
            ;
        } catch (IOException var13) {
            ;
        }

        return t;
    }

    public static UpdateResultBean parseAuthKey(String xml) {
        StringReader read = new StringReader(xml);
        InputSource source = new InputSource(read);
        UpdateResultBean t = new UpdateResultBean();
        SAXBuilder builder = new SAXBuilder();

        try {
            Document doc = builder.build(source);
            Element root = doc.getRootElement();
            if (root != null) {
                Attribute result = root.getAttribute("result");
                if (result != null) {
                    int nResult = parseInt(result.getValue(), 0);
                    t.setResult(nResult);
                    t.setErrMsg(getErrorMsg(nResult));
                }

                if (t.getResult() > 0) {
                    Element item = root.getChild("Item");
                    if (item != null) {
                        t.setCorpId(parseLong(item.getAttributeValue("cid"), 0L));
                        t.setStaffId(parseInt(item.getAttributeValue("sid"), 0));
                        t.setAuthKey(item.getAttributeValue("authkey"));
                    }
                }
            }
        } catch (JDOMException var9) {
            ;
        } catch (IOException var10) {
            ;
        }

        return t;
    }

    public static List<SendStateBean> parseSendState(String xml) {
        StringReader read = new StringReader(xml);
        InputSource source = new InputSource(read);
        SAXBuilder builder = new SAXBuilder();

        try {
            List<SendStateBean> listSendState = new ArrayList();
            Document doc = builder.build(source);
            Element root = doc.getRootElement();
            if (root != null) {
                Attribute result = root.getAttribute("result");
                if (result != null && parseInt(result.getValue(), -102) > 0) {
                    List<Element> listItem = root.getChildren("Item");
                    Iterator var10 = listItem.iterator();

                    while(var10.hasNext()) {
                        Element item = (Element)var10.next();
                        SendStateBean t = new SendStateBean();
                        t.setId(parseInt(item.getAttributeValue("id"), 0));
                        t.setMsgId(parseLong(item.getAttributeValue("msgid"), 0L));
                        t.setMobile(item.getAttributeValue("mobile"));
                        t.setStatus(parseInt(item.getAttributeValue("result"), 0));
                        t.setDetail(item.getAttributeValue("return"));
                        listSendState.add(t);
                    }
                }
            }

            return listSendState;
        } catch (JDOMException var12) {
            ;
        } catch (IOException var13) {
            ;
        }

        return null;
    }

    public static List<ReplyBean> parseReply(String xml) {
        StringReader read = new StringReader(xml);
        InputSource source = new InputSource(read);
        SAXBuilder builder = new SAXBuilder();

        try {
            List<ReplyBean> listReply = new ArrayList();
            Document doc = builder.build(source);
            Element root = doc.getRootElement();
            if (root != null) {
                Attribute result = root.getAttribute("result");
                if (result != null && parseInt(result.getValue(), -102) > 0) {
                    List<Element> listItem = root.getChildren("Item");
                    Iterator var10 = listItem.iterator();

                    while(var10.hasNext()) {
                        Element item = (Element)var10.next();
                        ReplyBean t = new ReplyBean();
                        t.setId(parseInt(item.getAttributeValue("id"), 0));
                        t.setMsgId(parseLong(item.getAttributeValue("msgid"), 0L));
                        long lTime = parseLong(item.getAttributeValue("time"), 0L);
                        if (lTime > 0L) {
                            t.setReplyTime(new Date(lTime));
                        }

                        t.setMobile(item.getAttributeValue("mobile"));
                        t.setContent(item.getAttributeValue("content"));
                        listReply.add(t);
                    }
                }
            }

            return listReply;
        } catch (JDOMException var14) {
            ;
        } catch (IOException var15) {
            ;
        }

        return null;
    }
}
