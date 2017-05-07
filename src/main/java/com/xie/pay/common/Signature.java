package com.xie.pay.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xie.config.MyWxPayConfig;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

public class Signature {
    private final static Logger logger = LoggerFactory.getLogger(Signature.class);

    /**
     * 签名算法
     *
     * @param o 要参与签名的数据对象
     * @return 签名
     * @throws IllegalAccessException
     */
    public static String getSign(Object o) {
        ArrayList<String> list = new ArrayList<String>();
        Class cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            try {
                f.setAccessible(true);
                if (f.get(o) != null && !TextUtils.isEmpty(f.get(o).toString())) {
                    String name = f.getName();
                    XStreamAlias anno = f.getAnnotation(XStreamAlias.class);
                    if (anno != null) {
                        name = anno.value();
                    }
                    list.add(name + "=" + f.get(o));
                }
            } catch (IllegalAccessException e) {
                logger.info("签名错误{}",o);
            }
        }
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        StringBuilder builder = new StringBuilder();
        builder.append(String.join("&", list));
        builder.append("&key=");
        builder.append(MyWxPayConfig.getAppKey());
        return MD5.MD5Encode(builder.toString()).toUpperCase();
    }

    public static void main2(String[] args) {
        /**
         * <xml>
         <appid>wxd930ea5d5a258f4f</appid>
         <mch_id>10000100</mch_id>
         <device_info>1000<device_info>
         <body>test</body>
         <nonce_str>ibuaiVcKdpRxkhJA</nonce_str>
         <sign>9A0A8659F005D6984697E2CA0A9CF3B7</sign>
         <xml>
         */


//        OrderInfo orderInfo = new OrderInfo();
//        orderInfo.setAppid("wxd930ea5d5a258f4f");
//        orderInfo.setMch_id("10000100");
//        orderInfo.setNonce_str("ibuaiVcKdpRxkhJA");
//        orderInfo.setBody("test");
//        orderInfo.setDevice_info("1000");

        /**
         * <xml>
         <appid>wx2421b1c4370ec43b</appid>
         <attach>支付测试</attach>
         <body>JSAPI支付测试</body>
         <mch_id>10000100</mch_id>
         <detail><![CDATA[{ "goods_detail":[ { "goods_id":"iphone6s_16G", "wxpay_goods_id":"1001", "goods_name":"iPhone6s 16G", "quantity":1, "price":528800, "goods_category":"123456", "body":"苹果手机" }, { "goods_id":"iphone6s_32G", "wxpay_goods_id":"1002", "goods_name":"iPhone6s 32G", "quantity":1, "price":608800, "goods_category":"123789", "body":"苹果手机" } ] }]]></detail>
         <nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>
         <notify_url>http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php</notify_url>
         <openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid>
         <out_trade_no>1415659990</out_trade_no>
         <spbill_create_ip>14.23.150.211</spbill_create_ip>
         <total_fee>1</total_fee>
         <trade_type>JSAPI</trade_type>
         <sign>0CB01533B8C1EF103065174F50BCA001</sign>
         </xml>
         */


//        OrderInfo orderInfo = new OrderInfo();
//        orderInfo.setAppid("wx2421b1c4370ec43b");
//        orderInfo.setAttach("支付测试");
//        orderInfo.setBody("JSAPI支付测试");
//        orderInfo.setMch_id("10000100");
//        orderInfo.setDetail("<![CDATA[{ \"goods_detail\":[ { \"goods_id\":\"iphone6s_16G\", \"wxpay_goods_id\":\"1001\", \"goods_name\":\"iPhone6s 16G\", \"quantity\":1, \"price\":528800, \"goods_category\":\"123456\", \"body\":\"苹果手机\" }, { \"goods_id\":\"iphone6s_32G\", \"wxpay_goods_id\":\"1002\", \"goods_name\":\"iPhone6s 32G\", \"quantity\":1, \"price\":608800, \"goods_category\":\"123789\", \"body\":\"苹果手机\" } ] }]]>");
//        orderInfo.setNonce_str("1add1a30ac87aa2db72f57a2375d8fec");
//        orderInfo.setNotify_url("http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php");
//        orderInfo.setOpenid("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
//        orderInfo.setOut_trade_no("1415659990");
//        orderInfo.setSpbill_create_ip("14.23.150.211");
//        orderInfo.setTotal_fee(1);
//        orderInfo.setTrade_type("JSAPI");
//
//        try {
//            System.out.printf(getSign(orderInfo));
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }

}
