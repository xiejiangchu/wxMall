//package com.xie.utils;
//
//import org.apache.commons.codec.binary.Base64;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.io.UnsupportedEncodingException;
//import java.security.InvalidAlgorithmParameterException;
//
///**
// * @Author xie
// * @Date 17/2/27 上午11:05.
// */
//public class WXDecodeUtils {
//
//    /**
//     * 解密用户敏感数据
//     *
//     * @param encryptedData 明文
//     * @param iv            加密算法的初始向量
//     * @param sessionId     会话ID
//     * @return
//     */
//    @RequestMapping(value = "/api/v1/wx/decodeUserInfo", method = RequestMethod.GET, produces = "application/json")
//    public String decodeUserInfo(@RequestParam(required = true, value = "encryptedData") String encryptedData,
//                                 @RequestParam(required = true, value = "iv") String iv,
//                                 @RequestParam(required = true, value = "sessionId") String sessionId) {
//
//        //从缓存中获取session_key
//        Object wxSessionObj = redisUtil.get(sessionId);
//        if (null == wxSessionObj) {
//            return "null";
//        }
//        String wxSessionStr = (String) wxSessionObj;
//        String sessionKey = wxSessionStr.split("#")[0];
//
//        try {
//            AES aes = new AES();
//            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
//            if (null != resultByte && resultByte.length > 0) {
//                String userInfo = new String(resultByte, "UTF-8");
//                return "success";
//            }
//        } catch (InvalidAlgorithmParameterException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return "fail";
//    }
//}
