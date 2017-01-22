package com.xie.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author xie
 * @Date 17/1/22 下午2:16.
 */
public class Region implements Serializable {

    private int id;
    private String province;
    private String city;
    private String district;
    private String road;
    private int is_open;
    private int order;
    private Date created_at;
    private Date updated_at;
}
