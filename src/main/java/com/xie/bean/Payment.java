package com.xie.bean;

import java.io.Serializable;

/**
 * @Author xie
 * @Date 17/1/22 下午2:15.
 */
public class Payment implements Serializable {

    private int id;
    private String code;
    private String name;
    private String app_key;
    private String app_secret;
    private String is_enabled;
}
