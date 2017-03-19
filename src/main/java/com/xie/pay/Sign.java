package com.xie.pay;

import com.alibaba.fastjson.JSONObject;
import com.xie.config.WxPayConfig;
import com.xie.pay.common.Signature;
import com.xie.pay.model.SignInfo;
import com.xie.utils.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 再签名
 */
public class Sign extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger L = Logger.getLogger(Sign.class);

    public Sign() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String repay_id = request.getParameter("repay_id");
            SignInfo signInfo = new SignInfo();
            signInfo.setAppId(WxPayConfig.getAppID());
            long time = System.currentTimeMillis() / 1000;
            signInfo.setTimeStamp(String.valueOf(time));
            signInfo.setNonceStr(StringUtils.randomNumber(32));
            signInfo.setRepay_id("prepay_id=" + repay_id);
            signInfo.setSignType("MD5");
            //生成签名
            String sign = Signature.getSign(signInfo);

            JSONObject json = new JSONObject();
            json.put("timeStamp", signInfo.getTimeStamp());
            json.put("nonceStr", signInfo.getNonceStr());
            json.put("package", signInfo.getRepay_id());
            json.put("signType", signInfo.getSignType());
            json.put("paySign", sign);
            response.getWriter().append(json.toJSONString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
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
