package com.xie.service;

import com.github.binarywang.wxpay.bean.result.WxPayOrderCloseResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;

import java.util.Map;

/**
 * Created by xie on 17/5/7.
 */
public interface PayService {

    WxPayUnifiedOrderResult unifiedOrder(int uid, int oid, String ip);

    Map<String,String> getPayInfo(String prepayId);

    WxPayOrderQueryResult queryOrder(String transactionId, String outTradeNo);

    WxPayOrderCloseResult closeOrder(String outTradeNo);

    WxPayOrderNotifyResult payResult(String xmlData);
}
