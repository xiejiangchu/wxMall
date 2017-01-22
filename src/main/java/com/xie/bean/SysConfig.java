package com.xie.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author xie
 * @Date 17/1/22 下午2:17.
 */
public class SysConfig implements Serializable {

    private int id;
    private String name;
    private String value;
    private Date created_at;
    private Date updated_at;
}
