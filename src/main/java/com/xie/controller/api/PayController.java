package com.xie.controller.api;

import com.github.binarywang.wxpay.bean.result.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.xie.response.BaseResponse;
import com.xie.service.PayService;
import com.xie.utils.IpUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
        Map<String, String> result = payService.getPayInfo(prepayId);
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

    @RequestMapping(value = "/payResult",method = RequestMethod.POST)
    public String payResult(@RequestBody String xmlData) throws WxErrorException {
        WxPayOrderNotifyResult wxPayOrderNotifyResult = payService.payResult(xmlData);
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        xStreamForRequestPostData.alias("xml", wxPayOrderNotifyResult.getClass());
        String result = xStreamForRequestPostData.toXML(wxPayOrderNotifyResult);
        return result;
    }

    /** TODO  还未实现
     * <pre>
     * 下载对账单
     * 商户可以通过该接口下载历史交易清单。比如掉单、系统错误等导致商户侧和微信侧数据不一致，通过对账单核对后可校正支付状态。
     * 注意：
     * 1、微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致，bill_type为REVOKED；
     * 2、微信在次日9点启动生成前一天的对账单，建议商户10点后再获取；
     * 3、对账单中涉及金额的字段单位为“元”。
     * 4、对账单接口只能下载三个月以内的账单。
     * 接口链接：https://api.mch.weixin.qq.com/pay/downloadbill
     * 详情请见: <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">下载对账单</a>
     * </pre>
     *
     * @param billDate   对账单日期 bill_date	下载对账单的日期，格式：20140603
     * @param billType   账单类型	bill_type	ALL，返回当日所有订单信息，默认值，SUCCESS，返回当日成功支付的订单，REFUND，返回当日退款订单
     * @param tarType    压缩账单	tar_type	非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
     * @param deviceInfo 设备号	device_info	非必传参数，终端设备号
     * @return 保存到本地的临时文件
     */
    @RequestMapping(value = "/downloadBill", method = RequestMethod.GET,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource getFile(@RequestParam(value = "billDate",required = true) String billDate,
                                      @RequestParam(value = "billType",required = true) String billType,
                                      @RequestParam(value = "tarType",required = false) String tarType,
                                      @RequestParam(value = "deviceInfo",required = false) String deviceInfo,
                                      HttpServletResponse response) {
        File file = payService.downloadBill(billDate, billType, tarType, deviceInfo);
        return new FileSystemResource(file);
    }

}
