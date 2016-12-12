package com.xie.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Banner implements Serializable {
    private Integer id;
    private String tip;
    private String url;
    private Byte is_show;
    private Integer order;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTip() {
        return this.tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Byte getIs_show() {
        return this.is_show;
    }

    public void setIs_show(Byte is_show) {
        this.is_show = is_show;
    }

    public Integer getOrder() {
        return this.order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Timestamp getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Timestamp getDeleted_at() {
        return this.deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }
} 

