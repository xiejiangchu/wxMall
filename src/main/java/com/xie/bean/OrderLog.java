package com.xie.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author xie
 * @Date 17/1/22 下午2:07.
 */
public class OrderLog implements Serializable {
    private int id;
    private int oid;
    private int type;
    private String operator;
    private String action;
    private String details;
    private Date created_at;
    private Date updated_at;
}
