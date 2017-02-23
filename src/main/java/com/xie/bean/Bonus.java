package com.xie.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Bonus implements Serializable {
    private Integer id;
    private Integer uid;
    private Integer tid;
    private Double money;
    private String name;
    private String desc;
    private Integer cid1;
    private Integer cd2;
    private Integer gid;
    private Double min_amount;
    private Integer is_enable;
    private Timestamp begin;
    private Timestamp end;
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

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Double getMoney() {
        return this.money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCid1() {
        return cid1;
    }

    public void setCid1(Integer cid1) {
        this.cid1 = cid1;
    }

    public Integer getCd2() {
        return cd2;
    }

    public void setCd2(Integer cd2) {
        this.cd2 = cd2;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Double getMin_amount() {
        return this.min_amount;
    }

    public void setMin_amount(Double min_amount) {
        this.min_amount = min_amount;
    }

    public Integer getIs_enable() {
        return is_enable;
    }

    public void setIs_enable(Integer is_enable) {
        this.is_enable = is_enable;
    }

    public Timestamp getBegin() {
        return this.begin;
    }

    public void setBegin(Timestamp begin) {
        this.begin = begin;
    }

    public Timestamp getEnd() {
        return this.end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
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

