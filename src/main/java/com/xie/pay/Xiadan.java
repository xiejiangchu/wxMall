package com.xie.pay;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.xie.config.WxPayConfig;
import com.xie.pay.common.HttpRequest;
import com.xie.pay.common.Signature;
import com.xie.pay.model.OrderInfo;
import com.xie.pay.model.OrderReturnInfo;
import com.xie.utils.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一下单接口
 */
public class Xiadan extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger L = Logger.getLogger(Xiadan.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Xiadan() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        try {
            String openid = request.getParameter("openid");
            OrderInfo order = new OrderInfo();
            order.setAppid(WxPayConfig.getAppID());
            order.setMch_id(WxPayConfig.getMch_id());
            order.setNonce_str(StringUtils.randomString(32));
            order.setBody("dfdfdf");
            order.setOut_trade_no(StringUtils.randomString(32));
            order.setTotal_fee(10);
            order.setSpbill_create_ip("123.57.218.54");
            order.setNotify_url("https://www.see-source.com/weixinpay/PayResult");
            order.setTrade_type("JSAPI");
            order.setOpenid(openid);
            order.setSign_type("MD5");
            //生成签名
            String sign = Signature.getSign(order);
            order.setSign(sign);


            String result = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", order);
            System.out.println(result);
            L.info("---------下单返回:" + result);
            XStream xStream = new XStream();
            xStream.alias("xml", OrderReturnInfo.class);

            OrderReturnInfo returnInfo = (OrderReturnInfo) xStream.fromXML(result);
            JSONObject json = new JSONObject();
            json.put("prepay_id", returnInfo.getPrepay_id());
            response.getWriter().append(json.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
            L.error("-------", e);
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
