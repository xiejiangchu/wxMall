package com.xie.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderCloseResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.xie.bean.Order;
import com.xie.bean.User;
import com.xie.config.MyWxPayConfig;
import com.xie.enums.PayState;
import com.xie.pay.common.Signature;
import com.xie.service.OrderService;
import com.xie.service.PayService;
import com.xie.service.UserService;
import com.xie.utils.StringUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xie on 17/5/7.
 */
@Service
public class PayServiceImpl implements PayService {
    private Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);
    private final static String TRADE_TYPE = "JSAPI";
    private final static String MD5 = "MD5";

    @Resource(name = "wxPayService")
    private WxPayService wxService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Override
    public WxPayUnifiedOrderResult unifiedOrder(int uid, int oid, String ip) {

        User user = userService.getById(uid);
        Order order = orderService.getById(oid);
        if (order.getPrepay_id() != null) {
            WxPayUnifiedOrderResult wxPayUnifiedOrderResult = new WxPayUnifiedOrderResult();
            wxPayUnifiedOrderResult.setPrepayId(order.getPrepay_id());
            return wxPayUnifiedOrderResult;
        }

        StringBuilder title = new StringBuilder();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH时mm分");
        title.append(simpleDateFormat.format(order.getCreated_at()));
        title.append(" - 订单号:");
        title.append(order.getNO());
        title.append(" - 金额:");
        title.append(order.getOrder_money());
        title.append(" - ");
        title.append(order.getReceiver());
        title.append(" - ");
        title.append(order.getOrderItems().get(0).getName());
        title.append("等,共");
        title.append(order.getOrder_amount());
        title.append("件商品");
        title.append("- 随机码");
        title.append(StringUtils.randomString(4));


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("order", order);

        SimpleDateFormat expireFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setBody(title.toString());
        wxPayUnifiedOrderRequest.setOutTradeNo(order.getNO());
        wxPayUnifiedOrderRequest.setDetail(jsonObject.toJSONString());
        wxPayUnifiedOrderRequest.setTotalFee((int) (order.getOrder_money() * 100));
        wxPayUnifiedOrderRequest.setSpbillCreateIp(ip);
        wxPayUnifiedOrderRequest.setNotifyURL(MyWxPayConfig.getNotify_url());
        wxPayUnifiedOrderRequest.setTradeType(TRADE_TYPE);
        wxPayUnifiedOrderRequest.setOpenid(user.getOpenId());
//        wxPayUnifiedOrderRequest.setTimeStart(expireFormat.format(DateTime.now().toDate()));
//        wxPayUnifiedOrderRequest.setTimeExpire(expireFormat.format(DateTime.now().plusHours(2).toDate()));
        wxPayUnifiedOrderRequest.setNonceStr(StringUtils.randomString(32));

        //生成签名
        String sign = Signature.getSign(wxPayUnifiedOrderRequest);
        wxPayUnifiedOrderRequest.setSign(sign);

        WxPayUnifiedOrderResult wxPayUnifiedOrderResult = null;
        try {
            wxPayUnifiedOrderResult = wxService.unifiedOrder(wxPayUnifiedOrderRequest);
            orderService.updatePrepayId(oid, wxPayUnifiedOrderResult.getPrepayId());
        } catch (WxErrorException e) {
            logger.info("请求支付出现异常{}", wxPayUnifiedOrderRequest);
        }
        return wxPayUnifiedOrderResult;
    }

    @Override
    public Map<String, String> getPayInfo(String prepayId) {
        if (org.apache.commons.lang3.StringUtils.isBlank(prepayId)) {
            logger.error("无法获取prepay id{}", prepayId);
        }
        Map<String, String> map = new HashMap<>();
        map.put("appId", MyWxPayConfig.getAppId());
        map.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        map.put("nonceStr", StringUtils.randomNumber(32));
        map.put("package", "prepay_id=" + prepayId);
        map.put("signType", MD5);
        map.put("paySign", SignUtils.createSign(map, MyWxPayConfig.getMchKey()));

        return map;


//        try {
//            long time = System.currentTimeMillis() / 1000;
//            SignInfo signInfo = new SignInfo();
//            signInfo.setAppId(MyWxPayConfig.getAppId());
//            signInfo.setTimeStamp(String.valueOf(time));
//            signInfo.setNonceStr(StringUtils.randomNumber(32));
//            signInfo.setRepay_id("prepay_id=" + prepayId);
//            signInfo.setSignType("MD5");
//            //生成签名
//            String sign = Signature.getSign(signInfo);
//
//            JSONObject json = new JSONObject();
//            json.put("timeStamp", signInfo.getTimeStamp());
//            json.put("nonceStr", signInfo.getNonceStr());
//            json.put("package", signInfo.getRepay_id());
//            json.put("signType", signInfo.getSignType());
//            json.put("paySign", sign);
//            return json;
//        } catch (Exception e) {
//            logger.info("签名失败");
//        }
//        return null;
    }

    @Override
    public WxPayOrderQueryResult queryOrder(String transactionId, String outTradeNo) {
        try {
            return wxService.queryOrder(transactionId, outTradeNo);
        } catch (WxErrorException e) {
            logger.error("查询订单失败{}", transactionId, outTradeNo, e.getMessage());
        }
        return null;
    }

    @Override
    public WxPayOrderCloseResult closeOrder(String outTradeNo) {
        try {
            WxPayOrderCloseResult orderCloseResult = wxService.closeOrder(outTradeNo);
            return orderCloseResult;
        } catch (WxErrorException e) {
            logger.error("关闭订单失败{}", outTradeNo, e.getMessage());
        }
        return null;
    }

    @Override
    public WxPayOrderNotifyResult payResult(String xmlData) throws WxErrorException {
        WxPayOrderNotifyResult result = wxService.getOrderNotifyResult(xmlData);
        String orderNo = result.getOutTradeNo();
        if (null != orderNo) {
            Order order = orderService.getByNo(orderNo);
            if (null == order) {
                logger.info("订单找不到{}", xmlData);
            } else {
                order.setPay_status(PayState.已支付.value());
                orderService.update(order);
            }

        }
        return result;
    }

    @Override
    public File downloadBill(String billDate, String billType, String tarType, String deviceInfo) {
        try {
            return wxService.downloadBill(billDate, billType, tarType, deviceInfo);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }
}
