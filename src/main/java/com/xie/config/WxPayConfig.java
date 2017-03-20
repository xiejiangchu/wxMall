package com.xie.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WxPayConfig {
    private static String appKey;
    //小程序ID
    private static String appID = "";
    //商户号
    private static String mch_id;
    //
    private static String appSecret = "";

    private static String notify_url;

    public static String getAppKey() {
        return appKey;
    }

    @Value("${wx.pay.appKey}")
    public static void setAppKey(String appKey) {
        WxPayConfig.appKey = appKey;
    }

    public static String getAppID() {
        return appID;
    }

    @Value("${wx.pay.appID}")
    public static void setAppID(String appID) {
        WxPayConfig.appID = appID;
    }

    public static String getMch_id() {
        return mch_id;
    }

    @Value("${wx.pay.mch_id}")
    public static void setMch_id(String mch_id) {
        WxPayConfig.mch_id = mch_id;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    @Value("${wx.pay.appSecret}")
    public static void setAppSecret(String appSecret) {
        WxPayConfig.appSecret = appSecret;
    }

    public static String getNotify_url() {
        return notify_url;
    }

    @Value("${wx.pay.notify_url}")
    public static void setNotify_url(String notify_url) {
        WxPayConfig.notify_url = notify_url;
    }
}
