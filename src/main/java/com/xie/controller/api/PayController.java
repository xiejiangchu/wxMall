package com.xie.controller.api;

import com.github.binarywang.wxpay.bean.result.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.xie.response.BaseResponse;
import com.xie.service.PayService;
import com.xie.utils.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Map;

/**
 * Created by xie on 17/5/7.
 */
@RestController
@RequestMapping(value = "/pay")
public class PayController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PayService payService;


    @RequestMapping(value = "/unifiedOrder", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse unifiedOrder(@RequestParam("oid") int oid, HttpServletRequest request) throws IllegalAccessException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, KeyStoreException, IOException {
        WxPayUnifiedOrderResult result = payService.unifiedOrder(getUid(), oid, IpUtils.getIpAddr(request));
        return BaseResponse.ok(result);
    }

    @RequestMapping(value = "/getPayInfo", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getPayInfo(@RequestParam("prepayId") String prepayId) {
        Map<String,String> result = payService.getPayInfo(prepayId);
        return BaseResponse.ok(result);
    }

//    @RequestMapping(value = "/payResult", method = RequestMethod.GET)
//    @ResponseBody
//    public String payResult(HttpServletRequest request) throws IOException {
//        String reqParams = StreamUtil.read(request.getInputStream());
//        logger.info("-------支付结果:" + reqParams);
//        StringBuffer sb = new StringBuffer("<xml><return_code>SUCCESS</return_code><return_msg>OK</return_msg></xml>");
//        return sb.toString();
//    }

    @RequestMapping(value = "/queryOrder", method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse queryOrder(@RequestParam(required = false) String transactionId,
                                   @RequestParam(required = false) String outTradeNo) {
        return BaseResponse.ok(payService.queryOrder(transactionId, outTradeNo));
    }

    @RequestMapping(value = "/closeOrder/{outTradeNo}", method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('ROLE_admin')")
    public BaseResponse closeOrder(@PathVariable String outTradeNo) {
        return BaseResponse.ok(payService.closeOrder(outTradeNo));
    }

    @RequestMapping(value = "/payResult", method = RequestMethod.POST)
    public WxPayOrderNotifyResult payResult(@RequestBody String xmlData) {
        return payService.payResult(xmlData);
    }


}
