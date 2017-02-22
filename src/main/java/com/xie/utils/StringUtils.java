package com.xie.utils;

import org.joda.time.DateTime;

import java.security.SecureRandom;

/**
 * @Author xie
 * @Date 17/2/22 下午5:39.
 */
public class StringUtils {
    //    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String AB = "0123456789";
    static SecureRandom rnd = new SecureRandom();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static String generateOrderNo() {
        String today = DateTime.now().toString("yyyyMMddhhMM");
        return today.concat(randomString(6));
    }
}
