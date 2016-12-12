package com.xie.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Cart implements Serializable {
    private Integer id;
    private Integer uid;
    private Integer gid;
    private Integer amount;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return this.uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGid() {
        return this.gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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
} 

